package br.com.tech4me.cadastro.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.cadastro.service.QuadroService;
import br.com.tech4me.cadastro.shared.QuadroCompletoDTO;
import br.com.tech4me.cadastro.shared.QuadroDTo;


@RestController
@RequestMapping("/quadros")
public class QuadroController {
   @Autowired
   private QuadroService servico;

   // CREATE
   @PostMapping
   public ResponseEntity<QuadroCompletoDTO> cadastrarPlaca(@RequestBody QuadroCompletoDTO dto) {
      return new ResponseEntity<>(servico.cadastrarQuadro(dto), HttpStatus.CREATED);
   }

   // READ
   @GetMapping
   public ResponseEntity<List<QuadroCompletoDTO>> ObterQuadros() {
      return new ResponseEntity<>(servico.obterQuadrosCadastrados(), HttpStatus.OK);
   }

   @GetMapping("/{id}")
   public ResponseEntity<QuadroDTo> obterPorId(@PathVariable String id) {
      return new ResponseEntity<>(servico.buscarQuadroPorId(id).get(), HttpStatus.OK);
   }

   // UPDATE
   @PutMapping("/{id}")
   public ResponseEntity<QuadroCompletoDTO> atualizarQuadroPorId(@PathVariable String id,
         @RequestBody QuadroCompletoDTO quadro) {
      Optional<QuadroCompletoDTO> quadroAtualizado = servico.atualizarQuadro(id, quadro);
      if (quadroAtualizado != null) {
         return new ResponseEntity<>(quadroAtualizado.get(), HttpStatus.OK);
      } else {
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

   }

   //DELETE
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deletarPorId(@PathVariable String id){
      servico.excluirPorId(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);

   }

}
