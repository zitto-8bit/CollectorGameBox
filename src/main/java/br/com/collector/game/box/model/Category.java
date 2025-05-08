package br.com.collector.game.box.model;

import java.util.HashSet;
import java.util.Set;

import br.com.collector.game.box.dto.CategoryDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer id;

    @Column(name = "category_name", nullable = false)
    private String name;

    @Column(name = "is_adult", nullable = false)
    private boolean isAdult;

    @ManyToMany(mappedBy = "categories")
    private Set<Game> games = new HashSet<>();
    
    public Category(CategoryDTO category) {
        this.name = category.getName();
        this.isAdult = category.isAdult();
    }
}