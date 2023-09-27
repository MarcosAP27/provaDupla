package br.com.tech4me.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tech4me.cadastro.model.Quadro;
@Repository
public interface QuadroRepositorio extends JpaRepository <Quadro, String> {
    
}
