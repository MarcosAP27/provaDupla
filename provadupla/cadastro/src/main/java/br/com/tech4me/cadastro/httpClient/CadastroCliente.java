package br.com.tech4me.cadastro.httpClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.tech4me.cadastro.model.Quadro;

@FeignClient("cadastro")
public interface CadastroCliente {

    @RequestMapping(method = RequestMethod.GET, value = "/quadros/{id}")
    Quadro obterQuadro(@PathVariable String id);
}
