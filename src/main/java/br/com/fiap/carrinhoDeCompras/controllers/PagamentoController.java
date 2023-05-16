package br.com.fiap.carrinhoDeCompras.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.carrinhoDeCompras.exceptions.RestNotFoundException;
import br.com.fiap.carrinhoDeCompras.models.Pagamento;
import br.com.fiap.carrinhoDeCompras.models.Produtos;
import br.com.fiap.carrinhoDeCompras.models.RestValidationError;
import br.com.fiap.carrinhoDeCompras.repository.PagamentoRepository;
import br.com.fiap.carrinhoDeCompras.repository.ProdutosRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pagamento")
public class PagamentoController {

   Logger log = LoggerFactory.getLogger(getClass());

   @Autowired
   PagamentoRepository repository;

   @GetMapping
   public List<Pagamento> index(){
      return repository.findAll();
   }

   @PostMapping
   public ResponseEntity<Pagamento> create(@RequestBody @Valid Pagamento pagamento){
      log.info("Cadastro do pagamento " + pagamento);
      repository.save(pagamento);
      return ResponseEntity.status(HttpStatus.CREATED).body(pagamento);
   }

   @GetMapping("{id}")
   public ResponseEntity<Pagamento> show(@PathVariable Long id){
      log.info("Detalhe do pagamento" + id);
      return ResponseEntity.ok(getPagamento(id));
   }

   @DeleteMapping("{id}")
   public ResponseEntity<Pagamento> destroy(@PathVariable Long id){
      log.info("Deletando o pagamento" + id);
      var pagamento = getPagamento(id);
      repository.delete(pagamento);   
      return ResponseEntity.noContent().build();
   }

   @PutMapping("{id}")
   public ResponseEntity<Pagamento> update(@PathVariable Long id, @RequestBody @Valid Pagamento pagamento){
      log.info("pagamento atualizado " + id);
      getPagamento(id);
      pagamento.setId(id);
      repository.save(pagamento);
      return ResponseEntity.ok(pagamento);   
   }

   private Pagamento getPagamento(Long id) {
      return repository.findById(id)
         .orElseThrow(() -> new RestNotFoundException("pagamento não encontrado"));
   }
   
}