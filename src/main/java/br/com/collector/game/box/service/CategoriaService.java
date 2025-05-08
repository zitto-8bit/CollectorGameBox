package br.com.collector.game.box.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.collector.game.box.dto.CategoryDTO;
import br.com.collector.game.box.model.Category;
import br.com.collector.game.box.repository.CategoryRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<CategoryDTO> buscarTodas() {
	    List<Category> categorias = categoryRepository.findAll();
	    
	    List<CategoryDTO> categoriasRetorno = categorias.stream()
	                                                    .map(category -> new CategoryDTO(category))
	                                                    .sorted(Comparator.comparing(CategoryDTO::getName))
	                                                    .collect(Collectors.toList());
	    
	    return categoriasRetorno;
	}



	
}
