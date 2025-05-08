package br.com.collector.game.box.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "GAMES")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Integer id;

    @Column(name = "game_name", nullable = false)
    private String name;

    @Column(name = "game_release", nullable = false)
    private Integer release;

    @Column(name = "game_plataform", nullable = false)
    private String plataform;

    @Lob
    @Column(name = "game_synopsis")
    private String synopsis;

    @Lob
    @Column(name = "game_image")
    private byte[] image;

    @Column(name = "is_adult", nullable = false)
    private boolean isAdult;
    
    @Column(name = "is_true", nullable = false)
    private boolean isTrue;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "GAME_CATEGORY",
        joinColumns = @JoinColumn(name = "game_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();
    
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsuarioGame> usuarioGames = new ArrayList<>();

	public Game(String name, Integer release, String plataform, String synopsis, byte[] image, boolean isAdult, boolean isTrue, 
			Set<Category> categories) {
		this.name = name;
		this.release = release;
		this.plataform = plataform;
		this.synopsis = synopsis;
		this.image = image;
		this.isAdult = isAdult;
		this.isTrue = isTrue;
		this.categories = categories;
	}
    
}
