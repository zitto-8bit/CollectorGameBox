package br.com.collector.game.box.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.collector.game.box.dto.RegistroDTO;
import br.com.collector.game.box.dto.UsuarioDTO;
import br.com.collector.game.box.dto.LoginDTO;
import br.com.collector.game.box.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("logar")
	public ResponseEntity<UsuarioDTO> logar(@RequestBody @Valid LoginDTO dadosLogin) {
		return new ResponseEntity<UsuarioDTO>(usuarioService.verificarDadosLogin(dadosLogin), HttpStatus.OK);
	}
	
	@PostMapping("registrar")
	public ResponseEntity<Void> registrar(@RequestBody @Valid RegistroDTO dadosCadastro) {
		usuarioService.registrar(dadosCadastro);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping("atualizar")
	public ResponseEntity<UsuarioDTO> atualizar(@RequestBody UsuarioDTO usuario) {
		return new ResponseEntity<UsuarioDTO>(this.usuarioService.atualizar(usuario), HttpStatus.OK);
	}
	
}
