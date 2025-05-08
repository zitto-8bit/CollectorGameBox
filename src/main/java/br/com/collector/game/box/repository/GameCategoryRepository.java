package br.com.collector.game.box.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.collector.game.box.model.GameCategory;
import br.com.collector.game.box.model.pk.GameCategoryId;

public interface GameCategoryRepository extends JpaRepository<GameCategory, GameCategoryId> {

}
