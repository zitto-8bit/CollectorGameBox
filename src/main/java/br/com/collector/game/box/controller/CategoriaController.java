package br.com.collector.game.box.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.collector.game.box.dto.CategoryDTO;
import br.com.collector.game.box.service.CategoriaService;

@RestController
@RequestMapping("categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("buscarTodas")
	public ResponseEntity<List<CategoryDTO>> buscarTodos() {
		return new ResponseEntity<List<CategoryDTO>>(categoriaService.buscarTodas(), HttpStatus.OK);
	}
	
}
