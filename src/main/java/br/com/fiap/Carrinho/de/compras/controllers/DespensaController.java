package br.com.fiap.Carrinho.de.compras.controller;

import org.springframework.sterotype.Controller;

@RestController
public class DespensaController {

   @GetMapping("/api/despesas")
   public String show() {
      return "despensa";
   }
   
}
