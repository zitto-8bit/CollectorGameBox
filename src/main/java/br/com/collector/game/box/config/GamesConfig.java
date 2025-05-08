package br.com.collector.game.box.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.collector.game.box.model.Category;
import br.com.collector.game.box.model.Game;
import br.com.collector.game.box.repository.CategoryRepository;
import br.com.collector.game.box.repository.GameRepository;

@Configuration
public class GamesConfig {

	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Bean(name = "gamesInit")
    CommandLineRunner init() {
        return args -> {
            for(Game game:populaTabelaGame()) {
            	gameRepository.save(game);            	
            }
        };
    }
	
	private List<Game> populaTabelaGame() {
		Set<Category> rpgCategories = new HashSet<>();
		Category rpgCategory = categoryRepository.findByName("RPG");
		rpgCategories.add(rpgCategory);

		Set<Category> adventureCategories = new HashSet<>();
		Category adventureCategory = categoryRepository.findByName("Aventura");
		adventureCategories.add(adventureCategory);

		Set<Category> strategyCategories = new HashSet<>();
		Category strategyCategory = categoryRepository.findByName("Estratégia");
		strategyCategories.add(strategyCategory);

		Set<Category> fightingCategories = new HashSet<>();
		Category fightingCategory = categoryRepository.findByName("Luta");
		fightingCategories.add(fightingCategory);

		Set<Category> simulationCategories = new HashSet<>();
		Category simulationCategory = categoryRepository.findByName("Simulação");
		simulationCategories.add(simulationCategory);

		Set<Category> actionCategories = new HashSet<>();
		Category actionCategory = categoryRepository.findByName("Ação");
		actionCategories.add(actionCategory);

		// Agrupamentos por categorias mais específicas
		Set<Category> horrorActionCategories = new HashSet<>();
		Category terrorCategory = categoryRepository.findByName("Terror");
		horrorActionCategories.add(terrorCategory);
		horrorActionCategories.add(actionCategory);

		Set<Category> racingSimulationCategories = new HashSet<>();
		Category corridaCategory = categoryRepository.findByName("Corrida");
		racingSimulationCategories.add(corridaCategory);
		racingSimulationCategories.add(simulationCategory);

		Set<Category> rpgAdventureCategories = new HashSet<>();
		rpgAdventureCategories.add(rpgCategory);
		rpgAdventureCategories.add(adventureCategory);

		Set<Category> strategySimulationCategories = new HashSet<>();
		strategySimulationCategories.add(strategyCategory);
		strategySimulationCategories.add(simulationCategory);

		Set<Category> fightingActionCategories = new HashSet<>();
		fightingActionCategories.add(fightingCategory);
		fightingActionCategories.add(actionCategory);

		Set<Category> simulationAdventureCategories = new HashSet<>();
		simulationAdventureCategories.add(simulationCategory);
		simulationAdventureCategories.add(adventureCategory);

		// Agora você pode organizar os jogos de acordo com as categorias agrupadas:

		List<Game> games = new LinkedList<>();

		games.add(new Game("Hades", 2020, "PC", "Roguelike com narrativa mitológica e combate rápido", retornaImagemEmByte("Hades.jpg"), true, true, rpgAdventureCategories));
		games.add(new Game("Dark Souls III", 2016, "PC", "RPG desafiador com ambientação sombria", retornaImagemEmByte("Dark Souls III.jpg"), true, true, rpgAdventureCategories));
		games.add(new Game("Sekiro: Shadows Die Twice", 2019, "PlayStation 4", "Ação furtiva com elementos de RPG no Japão feudal", retornaImagemEmByte("Sekiro Shadows Die Twice.jpg"), true, true, fightingActionCategories));
		games.add(new Game("The Sims 4", 2014, "PC", "Simulação de vida com infinitas possibilidades", retornaImagemEmByte("The Sims 4.jpg"), false, true, simulationCategories));
		games.add(new Game("Animal Crossing: New Horizons", 2020, "Switch", "Simulação de vila com personagens carismáticos", retornaImagemEmByte("Animal Crossing New Horizons.jpg"), false, true, simulationAdventureCategories));
		games.add(new Game("Persona 5", 2016, "PlayStation 4", "RPG japonês com vida escolar e combates por turnos", retornaImagemEmByte("Persona 5.jpg"), true, true, rpgAdventureCategories));
		games.add(new Game("Bayonetta", 2009, "Xbox 360", "Ação estilizada com bruxa poderosa", retornaImagemEmByte("Bayonetta.jpg"), true, true, actionCategories));
		games.add(new Game("Cuphead", 2017, "PC", "Run and gun com estilo de desenho animado clássico", retornaImagemEmByte("Cuphead.jpg"), false, true, actionCategories));
		games.add(new Game("Inside", 2016, "PC", "Aventura sombria com quebra-cabeças e suspense", retornaImagemEmByte("Inside.jpg"), false, true, adventureCategories));
		games.add(new Game("XCOM 2", 2016, "PC", "Estratégia tática com invasões alienígenas", retornaImagemEmByte("XCOM 2.jpg"), true, true, strategyCategories));
		games.add(new Game("Terraria", 2011, "PC", "Aventura sandbox com elementos de construção e combate", retornaImagemEmByte("Terraria.jpg"), false, true, rpgAdventureCategories));
		games.add(new Game("Celeste", 2018, "PC", "Plataforma desafiante com narrativa emocional", retornaImagemEmByte("Celeste.jpg"), false, true, adventureCategories));
		games.add(new Game("Dead Cells", 2018, "PC", "Roguelike de ação com progressão dinâmica", retornaImagemEmByte("Dead Cells.jpg"), true, true, actionCategories));
		games.add(new Game("Mortal Kombat 11", 2019, "PlayStation 4", "Jogo de luta brutal com personagens icônicos", retornaImagemEmByte("Mortal Kombat 11.jpg"), true, true, fightingCategories));
		games.add(new Game("Rocket League", 2015, "PC", "Simulação esportiva com carros e futebol", retornaImagemEmByte("Rocket League.jpg"), false, true, racingSimulationCategories));
		games.add(new Game("Stardew Valley", 2016, "PC", "Simulação de fazenda com RPG e relacionamentos", retornaImagemEmByte("Stardew Valley.jpg"), false, true, simulationAdventureCategories));
		games.add(new Game("Hollow Knight", 2017, "PC", "Aventura de ação em um mundo sombrio e interligado", retornaImagemEmByte("Hollow Knight.jpg"), false, true, rpgAdventureCategories));
		games.add(new Game("Apex Legends", 2019, "PC", "FPS de batalha real com heróis únicos", retornaImagemEmByte("Apex Legends.jpg"), true, true, actionCategories));
		games.add(new Game("Bioshock Infinite", 2013, "PC", "Aventura em cidade flutuante com narrativa profunda", retornaImagemEmByte("Bioshock Infinite.jpg"), true, true, horrorActionCategories));
		games.add(new Game("Dragon Age: Inquisition", 2014, "PC", "RPG com decisões impactantes e mundo vasto", retornaImagemEmByte("Dragon Age Inquisition.jpg"), true, true, rpgCategories));
		games.add(new Game("The Legend of Zelda", 1986, "NES", "Aventura em Hyrule com Link", retornaImagemEmByte("The_Legend_of_Zelda.png"), false, true, adventureCategories));
		games.add(new Game("The Witcher 3", 2015, "PC", "História de Geralt em um mundo sombrio", retornaImagemEmByte("The_Witcher_3.jpg"), true, true, rpgCategories));
		games.add(new Game("Super Mario Bros", 1985, "NES", "Clássico jogo de plataforma com Mario", retornaImagemEmByte("Super_Mario_Bros.jpg"), false, true, adventureCategories));
		games.add(new Game("Minecraft", 2011, "PC", "Jogo de construção e exploração no mundo de blocos", retornaImagemEmByte("Minecraft.jpg"), false, true, adventureCategories));
		games.add(new Game("Grand Theft Auto V", 2013, "PlayStation 3", "Mundo aberto com atividades ilícitas e ação intensa", retornaImagemEmByte("Grand_Theft_Auto_V.jpg"), true, false, actionCategories));
		games.add(new Game("Call of Duty: Modern Warfare", 2019, "PlayStation 4", "FPS com ação intensa e multiplayer", retornaImagemEmByte("Call_of_Duty_Modern_Warfare.jpg"), true, true, actionCategories));
		games.add(new Game("Red Dead Redemption 2", 2018, "PlayStation 4", "Mundo aberto de faroeste com narrativa envolvente", retornaImagemEmByte("Red Dead Redemption 2.jpg"), true, true, actionCategories));
		games.add(new Game("Assassin's Creed Odyssey", 2018, "PlayStation 4", "Aventura histórica com elementos de RPG", retornaImagemEmByte("Assassin's Creed Odyssey.jpg"), true, true, adventureCategories));
		games.add(new Game("Halo 5: Guardians", 2015, "Xbox One", "FPS futurista com ação e multiplayer", retornaImagemEmByte("Halo 5 Guardians.png"), true, true, actionCategories));
		games.add(new Game("FIFA 21", 2020, "PlayStation 4", "Simulação de futebol com times e jogadores reais", retornaImagemEmByte("FIFA 21.jpg"), false, true, simulationCategories));
		games.add(new Game("Pokémon Red", 1996, "Game Boy", "Jogo de RPG baseado em capturar Pokémon", retornaImagemEmByte("Pokemon Red.png"), false, true, rpgCategories));
		games.add(new Game("Final Fantasy VII", 1997, "PlayStation 1", "Clássico RPG com narrativa profunda", retornaImagemEmByte("Final Fantasy VII.jpg"), true, false, rpgCategories));
		games.add(new Game("Civilization VI", 2016, "PC", "Jogo de estratégia baseado em construir impérios", retornaImagemEmByte("Civilization VI.jpg"), false, false, strategyCategories));
		games.add(new Game("League of Legends", 2009, "PC", "Jogo MOBA de estratégia com equipes", retornaImagemEmByte("League of Legends.jpg"), true, true, strategyCategories));
		games.add(new Game("Starcraft II", 2010, "PC", "Jogo de estratégia em tempo real no espaço", retornaImagemEmByte("Starcraft II.jpg"), true, true, strategyCategories));
		games.add(new Game("The Elder Scrolls V: Skyrim", 2011, "PC", "RPG de mundo aberto com exploração", retornaImagemEmByte("The Elder Scrolls V Skyrim.jpg"), true, false, rpgCategories));
		games.add(new Game("Tetris", 1984, "Game Boy", "Clássico jogo de quebra-cabeça", retornaImagemEmByte("Tetris.jpg"), false, true, adventureCategories));
		games.add(new Game("World of Warcraft", 2004, "PC", "MMORPG com imersão em um mundo fantástico", retornaImagemEmByte("World of Warcraft.jpg"), true, true, rpgCategories));
		games.add(new Game("Street Fighter V", 2016, "PlayStation 4", "Jogo de luta com personagens clássicos", retornaImagemEmByte("Street Fighter V.png"), false, true, fightingCategories));
		games.add(new Game("Overwatch", 2016, "PC", "FPS com heróis e habilidades únicas", retornaImagemEmByte("Overwatch.jpg"), true, true, actionCategories));

        return games;
	}
	
	private byte[] retornaImagemEmByte(String caminho) {
		try {
			return Files.readAllBytes(Paths.get("src/main/resources/images/games/" + caminho));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
