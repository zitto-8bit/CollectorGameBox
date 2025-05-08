package br.com.collector.game.box.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representa um usuário do sistema.
 * 
 * Entidade mapeada para a tabela "USUARIO" e contém informações básicas do usuário,
 * como nome, e-mail, senha, data de nascimento e preferências de acessibilidade.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USUARIO")
public class Usuario {

    /**
     * Identificador único do usuário (chave primária).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    /**
     * Senha do usuário.
     * Este campo é obrigatório e deve conter no mínimo 6 caracteres.
     */
    @NotBlank(message = "{usuario.senha.obrigatoria}")
    @Size(min = 6, message = "{usuario.senha.minimo}")
    @Column(name = "user_password", nullable = false)
    private String userPassword;

    /**
     * Nome do usuário.
     * Este campo é obrigatório.
     */
    @NotBlank(message = "{usuario.nome.obrigatorio}")
    @Column(name = "user_name", nullable = false)
    private String userName;

    /**
     * Data de nascimento do usuário.
     * Este campo deve estar no passado.
     */
    @Past(message = "{usuario.dataNasc.passado}")
    @Column(name = "user_birth")
    private LocalDate userBirth;

    /**
     * E-mail do usuário.
     * Este campo é obrigatório e deve ser único no sistema.
     * O formato do e-mail também será validado.
     */
    @NotBlank(message = "{usuario.email.obrigatorio}")
    @Email(message = "{usuario.email.invalido}")
    @Column(name = "user_email", unique = true, nullable = false)
    private String userEmail;
    
    @Lob
    @Column(name = "user_icon")
    private byte[] userIcon;
    
    @Column(name = "user_bio")
    private String userBio;

    /**
     * Indica se o usuário possui acessibilidade ativada.
     * Este campo é representado por um único caractere ('S' para sim, 'N' para não).
     * A validação permite apenas esses dois valores.
     */
    @Pattern(regexp = "[SN]", message = "{usuario.acessibilidade.invalida}")
    @Column(name = "user_accessibility", length = 1)
    private String userAccessibility;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name="usuarios_acessos",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name="acess_id"))
    private List<Acesso> acessos;
    
    @OneToMany(mappedBy = "user")
    private Set<UsuarioGame> games = new HashSet<>();
}