package br.com.fiap.carrinhoDeCompras.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.carrinhoDeCompras.models.Pagamento;
import br.com.fiap.carrinhoDeCompras.models.Produtos;
import br.com.fiap.carrinhoDeCompras.models.Usuario;
import br.com.fiap.carrinhoDeCompras.repository.PagamentoRepository;
import br.com.fiap.carrinhoDeCompras.repository.ProdutosRepository;
import br.com.fiap.carrrinhoDeCompras.repository.UsuarioRepository;

import java.util.List;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

   @Autowired
   ProdutosRepository produtosRepository;

   @Autowired
   PagamentoRepository pagamentoRepository;

   
   @Autowired
   UsuarioRepository usuarioRepository;

   @Override
   public void run(String... args) throws Exception {

      Pagamento p1 = new Pagamento(1L, 4.75, "Boleto", true);
      Pagamento p2 = new Pagamento(2L, 3.30, "Boleto", false);
      Pagamento p3 = new Pagamento(3L, 1.75, "Débito", true);
      pagamentoRepository.saveAll(List.of(p1, p2, p3));

      produtosRepository.saveAll(List.of(
         Produtos.builder().price(4.75).categoria("Alimento").name("Café").pagamento(p1).build(),
         Produtos.builder().price(3.30).categoria("Alimento").name("Chocolate").pagamento(p2).build(),
         Produtos.builder().price(1.75).categoria("Alimento").name("Água").pagamento(p3).build()
      ));

      usuarioRepository.save(Usuario.builder()
      .nome("Vinícius Yoda")
      .email("vyoda@fiap.com.br")
      .senha("$2a$12$pMH3uGhwRXAaEq21jmmqn.PzxykI/HJyVAXM6sIQlcQ/2emqevaWC")
      .build()
  );
   }
}




