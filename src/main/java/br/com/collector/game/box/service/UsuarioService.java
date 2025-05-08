package br.com.collector.game.box.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.collector.game.box.auth.jwt.JwtService;
import br.com.collector.game.box.dto.LoginDTO;
import br.com.collector.game.box.dto.RegistroDTO;
import br.com.collector.game.box.dto.UsuarioDTO;
import br.com.collector.game.box.exception.CollectorGameBoxException;
import br.com.collector.game.box.impl.UserAuthenticated;
import br.com.collector.game.box.model.Acesso;
import br.com.collector.game.box.model.Usuario;
import br.com.collector.game.box.repository.UsuarioRepository;
import br.com.collector.game.box.util.Base64Utils;
import br.com.collector.game.box.util.MessageUtils;
import br.com.collector.game.box.util.RoleName;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private MessageUtils messageUtil;
    
    @Autowired
    private Base64Utils base64Util;
	
	public UsuarioDTO verificarDadosLogin(LoginDTO dadosLogin) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
				new UsernamePasswordAuthenticationToken(dadosLogin.getEmail(), dadosLogin.getSenha());
		
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		
		UserAuthenticated userAuthenticated = (UserAuthenticated) authentication.getPrincipal();
		
		return UsuarioDTO.builder()
				.id(userAuthenticated.getUsuario().getUserId())
				.nome(userAuthenticated.getUsuario().getUserName())
				.email(userAuthenticated.getUsuario().getUserEmail())
				.biografia(userAuthenticated.getUsuario().getUserBio())
				.icone(base64Util.encodeImg(userAuthenticated.getUsuario().getUserIcon()))
				.token(jwtService.generateToken(userAuthenticated))
				.acesso(userAuthenticated.getUsuario().getAcessos().get(0).getNome())
				.build();
	}
	
	public void registrar(RegistroDTO dados) {
		if(usuarioRepository.findByUserEmail(dados.getEmail()).isEmpty()) {
			usuarioRepository.save(toUsuario(dados));
		} else {
			throw new CollectorGameBoxException(messageUtil.getMessage("usuario.email.existente"));
		}
	}
	
	public UsuarioDTO atualizar(UsuarioDTO usuarioDto) {
		Optional<Usuario> usuarioOptinal = usuarioRepository.findByUserEmail(usuarioDto.getEmail());
		
		if(usuarioOptinal.isPresent()) {
			Usuario usuario = usuarioOptinal.get();
			
			Usuario usuarioAtualizado = usuarioRepository.save(dadosAtualizarUsuario(usuario, usuarioDto));
			usuarioDto.setNome(usuarioAtualizado.getUserName());
			usuarioDto.setIcone(base64Util.encodeImg(usuarioAtualizado.getUserIcon()));
			usuarioDto.setBiografia(usuarioAtualizado.getUserBio());
			
			return usuarioDto;
		} else {
			throw new CollectorGameBoxException(messageUtil.getMessage("usuario.desconectado"));
		}
	}
	
	
	/**
	 * Responsável por converter um objeto CadastroDTO em um objeto Usuario, 
	 * preenchendo os campos da entidade Usuario com os dados recebidos do DTO.
	 * 
	 * @param dadosCadastro
	 * @return Usuario
	 */
	private Usuario toUsuario(RegistroDTO dadosCadastro) {
		Usuario usuario = new Usuario();
		usuario.setUserName(dadosCadastro.getNome());
		usuario.setUserEmail(dadosCadastro.getEmail());
		
		usuario.setUserPassword(passwordEncoder.encode(dadosCadastro.getSenha()));
		
		LocalDate dataNasc;
		
		try {			
			dataNasc = LocalDate.parse(dadosCadastro.getDataNasc(), DateTimeFormatter.ofPattern("ddMMyyyy"));
		} catch (Exception e) {
			throw new CollectorGameBoxException(messageUtil.getMessage("usuario.dataNasc.invalida"));
		}
		
	    if (dataNasc.isAfter(LocalDate.now())) {
	        throw new CollectorGameBoxException(messageUtil.getMessage("usuario.dataNasc.passado"));
	    }
		
		usuario.setUserBirth(dataNasc);
		usuario.setUserAccessibility(dadosCadastro.getAcessibilidade());
		usuario.setAcessos(List.of(Acesso.builder().nome(RoleName.USER).build()));
		usuario.setUserIcon(retornaImagemEmByte("robo-icon.png"));
		return usuario;
	}
	
	private Usuario dadosAtualizarUsuario(Usuario usuario, UsuarioDTO usuarioDto) {
		String nome = usuarioDto.getNome();
		String icone = usuarioDto.getIcone();
		String bio = usuarioDto.getBiografia();
		
		if(nome != null)
			usuario.setUserName(nome);
		if(icone != null)
			usuario.setUserIcon(base64Util.decodeImg(icone));
		if(bio != null)
			usuario.setUserBio(bio);
		
		return usuario;
	}
	
	private byte[] retornaImagemEmByte(String caminho) {
		try {
			return Files.readAllBytes(Paths.get("src/main/resources/images/user/" + caminho));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
