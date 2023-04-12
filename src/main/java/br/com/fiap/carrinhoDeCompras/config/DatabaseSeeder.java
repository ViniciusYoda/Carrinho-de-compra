package br.com.fiap.carrinhoDeCompras.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.carrinhoDeCompras.models.Pagamento;
import br.com.fiap.carrinhoDeCompras.models.Produtos;
import br.com.fiap.carrinhoDeCompras.repositoty.ProdutosRepository;

import java.util.List;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

   @Autowired
   ProdutosRepository produtosRepository;

   @Override
   public void run(String... args) throws Exception {
      produtosRepository.saveAll(List.of(
         new Produtos(1l, "Café", 4.75, "Alimentos", null),
         new Produtos(2l, "Chocolate", 3.30, "Alimentos", null),
         new Produtos(3l, "Água", 1.75, "Alimentos", null)
      ));
   }
}