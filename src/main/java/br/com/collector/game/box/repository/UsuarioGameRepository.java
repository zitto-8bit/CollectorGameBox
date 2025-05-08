package br.com.collector.game.box.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.collector.game.box.model.UsuarioGame;
import br.com.collector.game.box.model.pk.UsuarioGameId;

public interface UsuarioGameRepository extends JpaRepository<UsuarioGame, UsuarioGameId> {

	List<UsuarioGame> findByUser_UserId(Long userId);
	
}
