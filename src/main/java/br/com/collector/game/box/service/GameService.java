package br.com.collector.game.box.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.collector.game.box.dto.CategoryDTO;
import br.com.collector.game.box.dto.GameDTO;
import br.com.collector.game.box.dto.JogoUsuarioDTO;
import br.com.collector.game.box.dto.UsuarioDTO;
import br.com.collector.game.box.model.Category;
import br.com.collector.game.box.model.Game;
import br.com.collector.game.box.model.Usuario;
import br.com.collector.game.box.model.UsuarioGame;
import br.com.collector.game.box.model.pk.UsuarioGameId;
import br.com.collector.game.box.repository.CategoryRepository;
import br.com.collector.game.box.repository.GameRepository;
import br.com.collector.game.box.repository.UsuarioGameRepository;
import br.com.collector.game.box.repository.UsuarioRepository;
import br.com.collector.game.box.util.Base64Utils;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioGameRepository usuarioGameRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
    private Base64Utils base64Util;
	
	@Cacheable(value = "buscarTodos", keyGenerator = "customKeyGenerator")
	public List<GameDTO> carregarTodos() {
		List<GameDTO> games = new ArrayList<>();
		
		for(Game game:gameRepository.findAllWhereIsTrueOrderByName()) {
			games.add(new GameDTO(game));
		}
		
		return games;
	}
	
	public List<GameDTO> carregarNaoAtivos() {
		List<GameDTO> games = new ArrayList<>();
		
		for(Game game:gameRepository.findAllWhereIsFalseOrderByName()) {
			games.add(new GameDTO(game));
		}
		
		return games;
	}
	
	@Cacheable(value = "buscarTodosLogado", keyGenerator = "customKeyGenerator")
	public List<GameDTO> carregarTodosUsuarioLogado(UsuarioDTO usuarioDto) {
		Usuario usuario = usuarioRepository.findByUserEmail(usuarioDto.getEmail()).get();
		
		List<GameDTO> games = new ArrayList<>();
		
		for(Game game:gameRepository.findAllWhereIsTrueOrderByName()) {
			Optional<UsuarioGame> jogoUsuario = usuarioGameRepository.findById(new UsuarioGameId(usuario.getUserId(), game.getId()));
			
			if(jogoUsuario.isPresent()) {
				games.add(new GameDTO(game, jogoUsuario.get()));				
			} else {
				games.add(new GameDTO(game, new UsuarioGame()));
			}
		}
		
		return games;
	}
	
	@CacheEvict(cacheNames = {"buscarTodos", "buscarTodosLogado"}, allEntries = true)
	public void salvarJogo(GameDTO gameDto) {
		JogoUsuarioDTO jogoUsuario = gameDto.getJogoUsuario();
		Game game = gameRepository.findById(gameDto.getId().longValue()).get();
		Usuario usuario = usuarioRepository.findById(gameDto.getJogoUsuario().getUsuarioDto().getId().longValue()).get();
		
		Optional<UsuarioGame> usuarioGameOptional = usuarioGameRepository.findById(new UsuarioGameId(usuario.getUserId(), game.getId()));
		UsuarioGame usuarioGame = null;
		
		if(usuarioGameOptional.isEmpty()) {
			usuarioGame = UsuarioGame.builder()
					.id(new UsuarioGameId(usuario.getUserId(), game.getId()))
					.game(game)
					.user(usuario)
					.owned(jogoUsuario.getIsPossui())
					.comment(jogoUsuario.getComentario())
					.rating(jogoUsuario.getNota())
					.build();			
		} else {
			usuarioGame = usuarioGameOptional.get();
			usuarioGame.setRating(jogoUsuario.getNota());
			usuarioGame.setOwned(jogoUsuario.getIsPossui());
			if(jogoUsuario.getComentario() != null) {
				usuarioGame.setComment(jogoUsuario.getComentario());				
			} else {
				usuarioGame.setComment(null);
			}
		}
		
		usuarioGameRepository.save(usuarioGame);
	}
	
	@CacheEvict("buscarTodos")
	public void aprovarJogo(Long id) {
		Game game = gameRepository.findById(id).get();
		
		game.setTrue(true);
		
		gameRepository.save(game);
	}
	
	public void sugerir(GameDTO gameDto) {
		Set<Category> categorias = new HashSet<>();

		for (CategoryDTO catDto : gameDto.getCategories()) {
			Category categoriaPersistida = categoryRepository.findByName(catDto.getName());
			categorias.add(categoriaPersistida);
		}

		Game game = new Game(
			gameDto.getName(),
			gameDto.getRelease(),
			gameDto.getPlataform(),
			gameDto.getSynopsis(),
			base64Util.decodeImgWithPrefix(gameDto.getImageBase64()),
			gameDto.isAdult(),
			gameDto.isTrue(),
			categorias
		);

		gameRepository.save(game);
	}

	
	public List<JogoUsuarioDTO> buscarUsuariosJogo(GameDTO gameDto) {
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<JogoUsuarioDTO> jogosUsuario = new ArrayList<>();
		
		for(Usuario usuario:usuarios) {
			Optional<UsuarioGame> usuarioGameOptional = usuarioGameRepository.findById(new UsuarioGameId(usuario.getUserId(), gameDto.getId()));
			
			if(usuarioGameOptional.isPresent()) {
				if(usuarioGameOptional.get().getComment() != null && 
						usuarioGameOptional.get().getComment().isBlank()) {
					usuarioGameOptional.get().setComment(null);
				}
				jogosUsuario.add(new JogoUsuarioDTO(usuarioGameOptional.get()));				
			}
		}
		
		return jogosUsuario;
	}
	
	public List<GameDTO> buscarJogosDoUsuario(Long idUsuario) {
		List<UsuarioGame> usuarioGames = usuarioGameRepository.findByUser_UserId(idUsuario).stream()
														      .filter(Objects::nonNull)
														      .filter(e -> e.getOwned() == true)
														      .collect(Collectors.toList());
		
		List<GameDTO> games = new ArrayList<>();
		
		for(UsuarioGame usuarioGame:usuarioGames) {
			games.add(new GameDTO(usuarioGame.getGame()));		
		}
		
	    return games;
	}
	
}
