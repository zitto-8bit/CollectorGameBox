package br.com.collector.game.box.model;

import java.math.BigDecimal;

import br.com.collector.game.box.model.pk.UsuarioGameId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "USUARIO_GAMES")
public class UsuarioGame {

    @EmbeddedId
    private UsuarioGameId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private Usuario user;

    @ManyToOne
    @MapsId("gameId")
    @JoinColumn(name = "game_id")
    private Game game;

    @Column(name = "rating")
    private BigDecimal rating;

    @Column(name = "owned")
    private Boolean owned;

    @Lob
    @Column(name = "comment")
    private String comment;
    
    public UsuarioGame() {
    	this.owned = false;
    }
}
