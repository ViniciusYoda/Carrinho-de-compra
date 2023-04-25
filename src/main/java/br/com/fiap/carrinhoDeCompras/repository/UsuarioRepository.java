package br.com.fiap.carrinhoDeCompras.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.carrinhoDeCompras.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
   
   Optional<Usuario> findByEmail(String email);
   
}
