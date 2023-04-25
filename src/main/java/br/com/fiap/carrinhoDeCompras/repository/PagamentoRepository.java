package br.com.fiap.carrinhoDeCompras.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.carrinhoDeCompras.models.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
   
}