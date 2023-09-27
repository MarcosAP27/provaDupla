package br.com.tech4me.cadastro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.cadastro.httpClient.CadastroCliente;
import br.com.tech4me.cadastro.model.Quadro;
import br.com.tech4me.cadastro.repository.QuadroRepositorio;
import br.com.tech4me.cadastro.shared.QuadroCompletoDTO;
import br.com.tech4me.cadastro.shared.QuadroDTo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class QuadroServiceImpl implements QuadroService {
    @Autowired
    private QuadroRepositorio listaQuadros;
    @Autowired
    CadastroCliente cliente;

    @Override
    public QuadroCompletoDTO cadastrarQuadro(QuadroCompletoDTO dto) {
        Quadro quadro = new Quadro(dto);
        listaQuadros.save(quadro);
        return new QuadroCompletoDTO(quadro.getId(),quadro.getNome(), quadro.getValor());
    }

    @Override
    public List<QuadroCompletoDTO> obterQuadrosCadastrados() {
        return listaQuadros.findAll().stream().map(q -> QuadroCompletoDTO.fromQuadro(q)).toList();
    }

    @CircuitBreaker(name = "obterQuadros", fallbackMethod = "fallbackQuadrosPorId")
    @Override

    public Optional<QuadroDTo> buscarQuadroPorId(String id) {
        Optional<Quadro> quadro = listaQuadros.findById(id);
        if (quadro.isPresent()) {
            Quadro quadr = cliente.obterQuadro(quadro.get().getId());
            Optional.of(QuadroDTo.fromQuadroDTo(quadro.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<QuadroCompletoDTO> atualizarQuadro(String id, QuadroCompletoDTO quadro) {
        Optional<Quadro> quadr = listaQuadros.findById(id);
        if (quadr.isPresent()) {
            Quadro quadroAtualizado = new Quadro(quadro);
            quadroAtualizado.setId(id);
            listaQuadros.save(quadroAtualizado);
            return Optional.of(QuadroCompletoDTO.fromQuadro(quadroAtualizado));
        }
        return Optional.empty();
    }

    @Override
    public void excluirPorId(String id) {
        listaQuadros.deleteById(id);
    }

    // CircuitBreaker (não consegui entender 100% essa parte)

    public Optional<QuadroDTo> fallbackQuadrosPorId(String id, Exception e) {
        Optional<Quadro> quadro = listaQuadros.findById(id);
        if (quadro.isPresent()) {
            QuadroDTo quadroFall = new QuadroDTo(quadro.get().getNome(),
                    quadro.get().getId(), quadro.get().getValor());
            return Optional.of(quadroFall);
        } else {
            return Optional.empty();
        }
    }
}
