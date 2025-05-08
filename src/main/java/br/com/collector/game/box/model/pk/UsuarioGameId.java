package br.com.collector.game.box.model.pk;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UsuarioGameId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
    private Integer userId;

    @Column(name = "game_id")
    private Integer gameId;

}
