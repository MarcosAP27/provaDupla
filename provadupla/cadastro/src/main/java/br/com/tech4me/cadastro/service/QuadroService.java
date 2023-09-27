package br.com.tech4me.cadastro.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.cadastro.shared.QuadroCompletoDTO;
import br.com.tech4me.cadastro.shared.QuadroDTo;

public interface QuadroService {

    QuadroCompletoDTO cadastrarQuadro(QuadroCompletoDTO dto);

    List<QuadroCompletoDTO> obterQuadrosCadastrados();

    Optional<QuadroCompletoDTO> atualizarQuadro(String id, QuadroCompletoDTO quadro);

    Optional<QuadroDTo> buscarQuadroPorId(String id);

    void excluirPorId(String id);
}
