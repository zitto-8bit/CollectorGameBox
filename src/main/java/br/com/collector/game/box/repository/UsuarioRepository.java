package br.com.collector.game.box.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.collector.game.box.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByUserEmail(String userEmail);
	
	Optional<Usuario> findByUserEmailAndUserPassword(String userEmail, String userPassword);

}
