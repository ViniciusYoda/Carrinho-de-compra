package br.com.fiap.Carrinho.de.compras.controller;

import org.springframework.sterotype.Controller;

@RestController
public class DespensaController {

   @GetMapping("/api/despesas")
   public Despesa show() {
      return new Despensa(new BigDecimal(100), LocalDate.now(), "cinema");
   }
   
}
