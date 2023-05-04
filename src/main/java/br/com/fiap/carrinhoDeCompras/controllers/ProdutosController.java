package br.com.fiap.carrinhoDeCompras.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.carrinhoDeCompras.exceptions.RestNotFoundException;
import br.com.fiap.carrinhoDeCompras.models.Produtos;
import br.com.fiap.carrinhoDeCompras.models.RestValidationError;
import br.com.fiap.carrinhoDeCompras.repository.ProdutosRepository;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

// import lombok.extern.slf4j.Slf4j;

import javax.swing.text.html.parser.Entity;

@RestController
@RequestMapping("/api/produtos")
// @Slf4j
public class ProdutosController {

   Logger log = LoggerFactory.getLogger(ProdutosController.class);

   @Autowired
   ProdutosRepository repository;

   @Autowired
   PagedResourcesAssembler<Object> assembler;

   @GetMapping
   public PagedModel<EntityModel<Object>> lista(@RequestParam(required = false) String busca, @PageableDefault(size = 5) Pageable pageable){
      Page<Produtos> produtos = (busca == null)?
         repository.findAll(pageable):
         repository.findByDescricaoContaining(busca, pageable);

      return assembler.toModel(produtos.map(Produtos::toEntityModel));
   }

   @PostMapping
   public ResponseEntity<Object> create(@RequestBody @Valid Produtos produto){
      log.info("Cadastro do produto " + produto);
      repository.save(produto);
      return ResponseEntity
      .created(produto.toEntityModel().getRequiredLink("self").toUri())
      .body(produto.toEntityModel());

   }

   @GetMapping("{id}")
   public EntityModel<Produtos> show(@PathVariable Long id){
      log.info("Detalhe do produto" + id);
      return getProdutos(id).toEntityModel();
   }

   @DeleteMapping("{id}")
   public ResponseEntity<Produtos> destroy(@PathVariable Long id){
      log.info("Apagando o produto" + id);
      var produto = getProdutos(id);

      repository.delete(produto);   

      return ResponseEntity.noContent().build();
   }

   @PutMapping("{id}")
   public EntityModel<Produtos> update(@PathVariable Long id, @RequestBody @Valid Produtos produto){
      log.info("Produto atualizado " + id);
      getProdutos(id);

      produto.setId(id);
      repository.save(produto);

      return produto.toEntityModel();

   }

   private Produtos getProdutos(Long id) {
      return repository.findById(id)
         .orElseThrow(() -> new RestNotFoundException("produto não encontrado"));
   }
   
}