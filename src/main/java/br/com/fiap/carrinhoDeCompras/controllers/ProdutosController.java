package br.com.fiap.carrinhoDeCompras.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.carrinhoDeCompras.exceptions.RestNotFoundException;
import br.com.fiap.carrinhoDeCompras.models.Produtos;
import br.com.fiap.carrinhoDeCompras.models.RestValidationError;
import br.com.fiap.carrinhoDeCompras.repositoty.ProdutosRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/produtos")
public class ProdutosController {

   Logger log = LoggerFactory.getLogger(ProdutosController.class);

   @Autowired
   ProdutosRepository repository;

   @GetMapping
   public List<Produtos> lista(){
      return repository.findAll();
   }

   @PostMapping
   public ResponseEntity<Object> create(@RequestBody @Valid Produtos produto){
      log.info("Cadastro do produto " + produto);
      repository.save(produto);
      return ResponseEntity.status(HttpStatus.CREATED).body(produto);
   }

   @GetMapping("{id}")
   public ResponseEntity<Produtos> show(@PathVariable Long id){
      log.info("Detalhe do produto" + id);
      var produto = repository.findById(id)
         .orElseThrow(() -> new RestNotFoundException("produto não encontrado"));

      return ResponseEntity.ok(produto);
   }

   @DeleteMapping("{id}")
   public ResponseEntity<Produtos> destroy(@PathVariable Long id){
      log.info("Apagando o produto" + id);
      var produto = repository.findById(id)
         .orElseThrow(() -> new RestNotFoundException("Erro ao apapgar, produto não encontrado"));

      repository.delete(produto);   

      return ResponseEntity.noContent().build();
   }

   @PutMapping("{id}")
   public ResponseEntity<Produtos> update(@PathVariable Long id, @RequestBody @Valid Produtos produto){
      log.info("Produto atualizado " + id);
      repository.findById(id)
         .orElseThrow(() -> new RestNotFoundException("Erro ao apagar, produto não encontrado"));

      produto.setId(id);
      repository.save(produto);

      return ResponseEntity.ok(produto);

    
   }
   
}