package br.com.fiap.carrinhoDeCompras.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.carrinhoDeCompras.models.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, Long> {

    Page<Produtos> findByDescricaoContaining(String busca, Pageable pageable);
   
}