package br.com.collector.game.box.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.collector.game.box.impl.UserAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
    private UserAuthenticationFilter userAuthenticationFilter;
	
	public static final String[] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
		    "/usuario/logar",
		    "/usuario/registrar",
		    "/game/buscarTodos",
		    "/h2-console",
		    "/h2-console/**",
		    "/game/buscarUsuariosJogo",
		    "/game/buscarJogosDoUsuario/*",
		    "/categoria/buscarTodas",
		    "/game/aprovarJogo/*"
		};
	
    public static final String [] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {
            "/usuario/atualizar",
    		"/game/salvarJogo",
    		"/game/sugerirJogo",
    		"/game/buscarNaoAtivos"
    };
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.cors(Customizer.withDefaults())
			.csrf(csrf -> csrf.disable())
			.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(auth -> auth
				.requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
				.requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated()
				.anyRequest().denyAll()
			).addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}
	
	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
