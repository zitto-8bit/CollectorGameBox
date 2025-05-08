package br.com.collector.game.box.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO utilizado para autenticação de usuários. Contém os campos necessários
 * para o login, com validações básicas.
 */
@Data
public class LoginDTO {

	/**
	 * E-mail do usuário. Deve estar em formato válido e não pode estar em branco.
	 */
	@Email(message = "{usuario.email.invalido}")
	@NotBlank(message = "{usuario.email.obrigatorio}")
	private String email;

	/**
	 * Senha do usuário. Campo obrigatório.
	 */
	@NotBlank(message = "{usuario.senha.obrigatoria}")
	private String senha;

}
