package br.com.tech4me.cadastro.model;

import br.com.tech4me.cadastro.shared.QuadroCompletoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(schema = "quadrosquadros" , name = "quadros")
public class Quadro {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
    private Double valor;
    
    public Quadro(QuadroCompletoDTO dto){
        setId(dto.id());
        setNome(dto.nome());
        setValor(dto.valor());
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
