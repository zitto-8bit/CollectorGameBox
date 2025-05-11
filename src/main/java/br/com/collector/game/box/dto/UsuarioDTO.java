package br.com.collector.game.box.dto;

import br.com.collector.game.box.util.RoleName;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuarioDTO {

	private Integer id;
	private String nome;
	private String email;
	private String biografia;
	private String icone;
	private String token;
	private String acessibilidade;
	@Enumerated(EnumType.STRING)
    private RoleName acesso;
	
}
