package br.com.fiap.carrinhoDeCompras.repositoty;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.carrinhoDeCompras.models.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, Long> {

    Page<Produtos> findByDescricaoContaining(String busca, Pageable pageable);
   

    // @Query("SELECT d FROM Despesa d WHERE d.descricao LIKE %?1%") //JPQL
    // @Query("SELECT d FROM Despesa d ORDER BY d.id LIMIT ?1 OFFSET ?2")
    // List<Despesa> buscarPaginado(int tamanho, int offset);
}