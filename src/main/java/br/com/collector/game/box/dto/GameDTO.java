package br.com.collector.game.box.dto;

import java.util.Base64;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.collector.game.box.model.Game;
import br.com.collector.game.box.model.UsuarioGame;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GameDTO {

	private Integer id;
    private String name;
    private Integer release;
    private String plataform;
    private String synopsis;
    private String imageBase64;
    @JsonProperty("adult")
    private boolean isAdult;
    private boolean isTrue;
    private Set<CategoryDTO> categories;
    private JogoUsuarioDTO jogoUsuario;

    public GameDTO(Game game) {
    	this.id = game.getId();
        this.name = game.getName();
        this.release = game.getRelease();
        this.plataform = game.getPlataform();
        this.synopsis = game.getSynopsis();
        this.isAdult = game.isAdult();
        this.isTrue = game.isTrue();

        if (game.getImage() != null) {
            this.imageBase64 = Base64.getEncoder().encodeToString(game.getImage());
        }

        if (game.getCategories() != null) {
            this.categories = game.getCategories().stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toSet());
        }
    }
    
    public GameDTO(Game game, UsuarioGame usuarioGame) {
    	this.id = game.getId();
        this.name = game.getName();
        this.release = game.getRelease();
        this.plataform = game.getPlataform();
        this.synopsis = game.getSynopsis();
        this.isAdult = game.isAdult();
        this.isTrue = game.isTrue();
        
        if(usuarioGame.getUser() != null) {
        	this.jogoUsuario = new JogoUsuarioDTO(usuarioGame);        	
        } else {
        	this.jogoUsuario = new JogoUsuarioDTO();    
        }

        if (game.getImage() != null) {
            this.imageBase64 = Base64.getEncoder().encodeToString(game.getImage());
        }

        if (game.getCategories() != null) {
            this.categories = game.getCategories().stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toSet());
        }
    }
}
