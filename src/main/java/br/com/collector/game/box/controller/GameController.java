package br.com.collector.game.box.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.collector.game.box.dto.GameDTO;
import br.com.collector.game.box.dto.JogoUsuarioDTO;
import br.com.collector.game.box.dto.UsuarioDTO;
import br.com.collector.game.box.service.GameService;

@RestController
@RequestMapping("game")
public class GameController {

	@Autowired
	private GameService gameService;
	
	@GetMapping("buscarTodos")
	public ResponseEntity<List<GameDTO>> buscarTodos() {
		return new ResponseEntity<List<GameDTO>>(gameService.carregarTodos(), HttpStatus.OK);
	}
	
	@GetMapping("buscarNaoAtivos")
	public ResponseEntity<List<GameDTO>> buscarNaoAtivos() {
		return new ResponseEntity<List<GameDTO>>(gameService.carregarNaoAtivos(), HttpStatus.OK);
	}
	
	@PostMapping("buscarTodos")
	public ResponseEntity<List<GameDTO>> buscarTodos(@RequestBody UsuarioDTO usuarioDto) {
		return new ResponseEntity<List<GameDTO>>(gameService.carregarTodosUsuarioLogado(usuarioDto), HttpStatus.OK);
	}
	
	@PostMapping("salvarJogo")
	public ResponseEntity<Void> salvarJogo(@RequestBody GameDTO gameDto) {
		gameService.salvarJogo(gameDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("aprovarJogo/{idJogo}")
	public ResponseEntity<Void> aprovarJogo(@PathVariable Long idJogo) {
		gameService.aprovarJogo(idJogo);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("reprovarJogo/{idJogo}")
	public ResponseEntity<Void> reprovarJogo(@PathVariable Long idJogo) {
		gameService.reprovarJogo(idJogo);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("buscarUsuariosJogo")
	public ResponseEntity<List<JogoUsuarioDTO>> buscarUsuariosJogo(@RequestBody GameDTO gameDto) {
		return new ResponseEntity<List<JogoUsuarioDTO>>(gameService.buscarUsuariosJogo(gameDto), HttpStatus.OK);
	}
	
	@GetMapping("buscarJogosDoUsuario/{idUsuario}")
	public ResponseEntity<List<GameDTO>> buscarJogosDoUsuario(@PathVariable Long idUsuario) {
		return new ResponseEntity<List<GameDTO>>(gameService.buscarJogosDoUsuario(idUsuario), HttpStatus.OK);
	}
	
	@PostMapping("sugerirJogo")
	public ResponseEntity<Void> sugerirJogo(@RequestBody GameDTO gameDto) {
		gameService.sugerir(gameDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
}
