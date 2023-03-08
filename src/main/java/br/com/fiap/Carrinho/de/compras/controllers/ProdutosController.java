package br.com.fiap.Carrinho.de.compras.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.dindin.models.Produtos;

@RestController
public class ProdutosController {

   @GetMapping("/api/produtos/{id}")
   public Produtos show() {
      return new Produtos(1, "Arroz", 4.15);
   }
   
}
