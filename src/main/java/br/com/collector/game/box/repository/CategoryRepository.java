package br.com.collector.game.box.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.collector.game.box.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByName(String name);
	
}
