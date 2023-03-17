package br.com.fiap.Carrinho.de.compras.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.dindin.models.Produtos;

@RestController
public class ProdutosController {

   Logger log = LoggerFactory.getLogger(ProdutosController.class);

   List<Produtos> produtos = new ArrayList<>();

   @GetMapping("/api/produtos")
   public List<Produto> lista(){
      return produtos
   }

   @PostMapping("/api/produto")
   public ResponseEntity<Produto> create(@RequestBody Prdoutos produto){
      log.info("Cadastro do produto " _ produto);
      produtos.setId(produtos.size() + 1l);
      produtos.add(produto);
      return ResponseEntity.status(HttpStatus.CREATED).body(produto);
   }

   @GetMapping("/api/produto/{id}")
   public ResponseEntity<Produtos> show(@PathVariable Long id){
      log.info("Detalhe do produto" + id);
      var getProduto = produtos.stream().filter(p -> p.getId().equals(id)).findFirst();

      if (getProduto.isEmpty())
         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

      return ResponseEntity.ok(getProduto.get());
   }

   @DeleteMapping("/api/produto/{id}")
   public ResponseEntity<Produtos> destroy(@PathVariable Long id){
      log.info("Apagando o produto" + id);
      var getProduto = produtos.stream().filter(p -> p.getId().equals(id)).findFirst();

      if (getProduto.isEmpty())
         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            produtos.remove(getProduto.get());

      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
   }

   @PutMapping("/api/produtos/{id}")
   public ResponseEntity<Produtos> update(@PathVariable Long id, @RequestBody Produtos produto){
      log.info("Produto atualizado " + id);
      var getProduto = produtos.stream().filter(p -> p.getId().equals(id)).findFirst();

      if (getProduto.isEmpty())
         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

      produtos.remove(getProduto.get());
      produto.setId(id);
      produtos.add(produto);

      return ResponseEntity.ok(produto)
   }
   
}
