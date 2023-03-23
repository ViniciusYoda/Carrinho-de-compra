package br.com.fiap.carrinhoDeCompras.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.carrinhoDeCompras.models.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, Long> {
   
}