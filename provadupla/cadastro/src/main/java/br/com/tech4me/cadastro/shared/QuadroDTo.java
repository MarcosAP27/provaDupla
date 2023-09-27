package br.com.tech4me.cadastro.shared;

import br.com.tech4me.cadastro.model.Quadro;

public record QuadroDTo (String id, String nome, Double valor) {
    
    public static QuadroDTo fromQuadroDTo(Quadro quadro){
        return new QuadroDTo(quadro.getId(), quadro.getNome(), quadro.getValor());
    }
    
}
