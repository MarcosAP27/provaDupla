package br.com.tech4me.cadastro.shared;

import br.com.tech4me.cadastro.model.Quadro;

public record QuadroCompletoDTO (String id, String nome, Double valor) {
    public static QuadroCompletoDTO fromQuadro (Quadro quadro){
        return new QuadroCompletoDTO(quadro.getId(),quadro.getNome(), quadro.getValor());
    }
}
    
