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
		usuario.setUserPassword(passwordEncoder.encode("123456"));
		usuario.setUserBirth(LocalDate.parse("28052004", DateTimeFormatter.ofPattern("ddMMyyyy")));
		usuario.setUserAccessibility("N");
		usuario.setAcessos(List.of(Acesso.builder().nome(RoleName.ADMIN).build()));
		usuario.setUserIcon(retornaImagemEmByte("alan-icon.png"));
		usuarioRepository.save(usuario);
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
