package br.com.collector.game.box.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.collector.game.box.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
	
	@Cacheable("buscarTodos")
	@Query("SELECT g FROM Game g WHERE g.isTrue = TRUE ORDER BY g.name ASC")
    List<Game> findAllWhereIsTrueOrderByName();
	
	@Query("SELECT g FROM Game g WHERE g.isTrue = FALSE ORDER BY g.name ASC")
    List<Game> findAllWhereIsFalseOrderByName();
	
}
