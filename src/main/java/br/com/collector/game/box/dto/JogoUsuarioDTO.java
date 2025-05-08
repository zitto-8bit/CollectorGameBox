package br.com.collector.game.box.dto;

import java.math.BigDecimal;
import java.util.Base64;

import br.com.collector.game.box.model.UsuarioGame;
import lombok.Data;

@Data
public class JogoUsuarioDTO {

    private BigDecimal nota;
    private Boolean isPossui;
    private String comentario;
    private UsuarioDTO usuarioDto;
	
    public JogoUsuarioDTO(UsuarioGame usuarioGame) {
    	this.nota = usuarioGame.getRating();
    	this.isPossui = usuarioGame.getOwned();
    	this.comentario = usuarioGame.getComment();
    	this.usuarioDto = UsuarioDTO.builder()
    			.id(usuarioGame.getUser().getUserId())
				.nome(usuarioGame.getUser().getUserName())
				.email(usuarioGame.getUser().getUserEmail())
				.biografia(usuarioGame.getUser().getUserBio())
				.icone(Base64.getEncoder().encodeToString(usuarioGame.getUser().getUserIcon()))
				.build();
    }
    
    public JogoUsuarioDTO() {
    	this.isPossui = false;
    }
    
}
