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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.carrinhoDeCompras.exceptions.RestNotFoundException;
import br.com.fiap.carrinhoDeCompras.models.Produtos;
import br.com.fiap.carrinhoDeCompras.models.RestValidationError;
import br.com.fiap.carrinhoDeCompras.repositoty.ProdutosRepository;
import br.com.fiap.carrinhoDeCompras.repositoty.PagamentoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/produtos")
@Slf4j
public class ProdutosController {

   // Logger log = LoggerFactory.getLogger(ProdutosController.class);

   @Autowired
   ProdutosRepository produtosRepository;

   @Autowired
   PagamentoRepository pagamentoRepository;

   @GetMapping
   public Page<Produtos> lista(@RequestParam(required = false) String busca, @PageableDefault(size = 5) Pageable pageable){
      if (busca == null) return produtosRepository.findAll(pageable);
      return produtosRepository.findByDescricaoContaining(busca, pageable);
   }

   @PostMapping
   public ResponseEntity<Object> create(@RequestBody @Valid Produtos produto){
      log.info("Cadastro do produto " + produto);
      produtosRepository.save(produto);
      produto.setPagamento(pagamentoRepository.findById(produto.getPagamento().getId()).get());
      return ResponseEntity.status(HttpStatus.CREATED).body(produto);
   }

   @GetMapping("{id}")
   public ResponseEntity<Produtos> show(@PathVariable Long id){
      log.info("Detalhe do produto" + id);
      var produto = produtosRepository.findById(id)
         .orElseThrow(() -> new RestNotFoundException("produto não encontrado"));

      return ResponseEntity.ok(produto);
   }

   @DeleteMapping("{id}")
   public ResponseEntity<Produtos> destroy(@PathVariable Long id){
      log.info("Apagando o produto" + id);
      var produto = produtosRepository.findById(id)
         .orElseThrow(() -> new RestNotFoundException("Erro ao apapgar, produto não encontrado"));

      produtosRepository.delete(produto);   

      return ResponseEntity.noContent().build();
   }

   @PutMapping("{id}")
   public ResponseEntity<Produtos> update(@PathVariable Long id, @RequestBody @Valid Produtos produto){
      log.info("Produto atualizado " + id);
      produtosRepository.findById(id)
         .orElseThrow(() -> new RestNotFoundException("Erro ao atualizar, produto não encontrado"));

      produto.setId(id);
      produtosRepository.save(produto);

      return ResponseEntity.ok(produto);

    
   }
   
}