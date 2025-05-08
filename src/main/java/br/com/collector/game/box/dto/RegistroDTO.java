package br.com.collector.game.box.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO para cadastro de usuários. Contém as informações essenciais para o
 * registro, como nome, e-mail, senha, data de nascimento e acessibilidade, com
 * validações para garantir a integridade dos dados.
 */
@Data
public class RegistroDTO {

	/**
	 * Nome do usuário. Não pode ser vazio.
	 */
	@NotBlank(message = "{usuario.nome.obrigatorio}")
	private String nome;

	/**
	 * E-mail do usuário. Não pode ser vazio e deve ser válido.
	 */
	@NotBlank(message = "{usuario.email.obrigatorio}")
	@Email(message = "{usuario.email.invalido}")
	private String email;

	/**
	 * Senha do usuário. Não pode ser vazia e deve ter no mínimo 6 caracteres.
	 */
	@NotBlank(message = "{usuario.senha.obrigatoria}")
	@Size(min = 6, message = "{usuario.senha.minimo}")
	private String senha;

	/**
	 * Data de nascimento do usuário. Deve ser no passado.
	 */
	@NotBlank(message = "{usuario.dataNasc.obrigatoria}")
	private String dataNasc;

	/**
	 * Acessibilidade do usuário. Deve ser 'S' ou 'N'.
	 */
	@NotNull(message = "{usuario.acessibilidade.obrigatoria}")
	@Pattern(regexp = "[SN]", message = "{usuario.acessibilidade.obrigatoria}")
	private String acessibilidade;
}
