package br.com.collector.game.box.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.collector.game.box.model.Acesso;
import br.com.collector.game.box.model.Usuario;
import br.com.collector.game.box.repository.UsuarioRepository;
import br.com.collector.game.box.util.RoleName;

@Configuration
public class AdminConfig {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Bean(name = "adminInit")
    CommandLineRunner init() {
        return args -> {
        	criaAdmins();
        };
    }
	
	private void criaAdmins() {
		Usuario usuario = new Usuario();
		usuario.setUserName("Matseiy");
		usuario.setUserEmail("matheus@admin.com");
		usuario.setUserPassword(passwordEncoder.encode("admin"));
		usuario.setUserBirth(LocalDate.parse("28052004", DateTimeFormatter.ofPattern("ddMMyyyy")));
		usuario.setUserAccessibility("N");
		usuario.setAcessos(List.of(Acesso.builder().nome(RoleName.ADMIN).build()));
		usuario.setUserIcon(retornaImagemEmByte("alan-icon.png"));
		usuarioRepository.save(usuario);
		
		Usuario usuario1 = new Usuario();
		usuario1.setUserName("Gustavo");
		usuario1.setUserEmail("gustavo@admin.com");
		usuario1.setUserPassword(passwordEncoder.encode("admin"));
		usuario1.setUserBirth(LocalDate.parse("15031998", DateTimeFormatter.ofPattern("ddMMyyyy")));
		usuario1.setUserAccessibility("S");
		usuario1.setAcessos(List.of(Acesso.builder().nome(RoleName.ADMIN).build()));
		usuario1.setUserIcon(retornaImagemEmByte("x-icon.png"));
		usuarioRepository.save(usuario1);

		Usuario usuario2 = new Usuario();
		usuario2.setUserName("Lyn");
		usuario2.setUserEmail("lyn@admin.com");
		usuario2.setUserPassword(passwordEncoder.encode("admin"));
		usuario2.setUserBirth(LocalDate.parse("10071990", DateTimeFormatter.ofPattern("ddMMyyyy")));
		usuario2.setUserAccessibility("N");
		usuario2.setAcessos(List.of(Acesso.builder().nome(RoleName.ADMIN).build()));
		usuario2.setUserIcon(retornaImagemEmByte("niko-icon.png"));
		usuarioRepository.save(usuario2);

		Usuario usuario3 = new Usuario();
		usuario3.setUserName("Laryssa");
		usuario3.setUserEmail("laryssa@admin.com");
		usuario3.setUserPassword(passwordEncoder.encode("admin"));
		usuario3.setUserBirth(LocalDate.parse("02022000", DateTimeFormatter.ofPattern("ddMMyyyy")));
		usuario3.setUserAccessibility("S");
		usuario3.setAcessos(List.of(Acesso.builder().nome(RoleName.ADMIN).build()));
		usuario3.setUserIcon(retornaImagemEmByte("lary-icon.png"));
		usuarioRepository.save(usuario3);

		Usuario usuario4 = new Usuario();
		usuario4.setUserName("Claudio");
		usuario4.setUserEmail("claudio@admin.com");
		usuario4.setUserPassword(passwordEncoder.encode("admin"));
		usuario4.setUserBirth(LocalDate.parse("25081995", DateTimeFormatter.ofPattern("ddMMyyyy")));
		usuario4.setUserAccessibility("N");
		usuario4.setAcessos(List.of(Acesso.builder().nome(RoleName.ADMIN).build()));
		usuario4.setUserIcon(retornaImagemEmByte("pan-icon.png"));
		usuarioRepository.save(usuario4);

		Usuario usuario6 = new Usuario();
		usuario6.setUserName("Tester");
		usuario6.setUserEmail("teste@teste.com");
		usuario6.setUserPassword(passwordEncoder.encode("admin"));
		usuario6.setUserBirth(LocalDate.parse("11061985", DateTimeFormatter.ofPattern("ddMMyyyy")));
		usuario6.setUserAccessibility("N");
		usuario6.setAcessos(List.of(Acesso.builder().nome(RoleName.ADMIN).build()));
		usuario6.setUserIcon(retornaImagemEmByte("robo-icon.png"));
		usuarioRepository.save(usuario6);
	}
	
	private byte[] retornaImagemEmByte(String caminho) {
		try {
			return Files.readAllBytes(Paths.get("src/main/resources/images/user/" + caminho));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
