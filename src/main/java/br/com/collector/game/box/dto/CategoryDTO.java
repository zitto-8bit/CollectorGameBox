package br.com.collector.game.box.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.collector.game.box.model.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDTO {

    private String name;
    
    @JsonProperty("adult")
    private boolean isAdult;

    public CategoryDTO(Category category) {
        this.name = category.getName();
        this.isAdult = category.isAdult();
    }

}
