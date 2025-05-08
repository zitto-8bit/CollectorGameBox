package br.com.collector.game.box.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private static final String[] ORIGINS = {
			"http://localhost:4200",
			"https://collectorgamebox.netlify.app",
			"http://26.248.54.3:4200",
			"http://192.168.15.25:4200",
			"https://hoppscotch.io"
			};
	
	private static final String[] METHODS = {
			"GET", "POST", "PUT", "DELETE"
			};
	
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(ORIGINS)
        		.allowedOriginPatterns("*")
                .allowedMethods(METHODS)
                .allowedHeaders("Authorization", "Content-Type")
                .allowCredentials(true);
    }
    
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(ORIGINS));
        configuration.setAllowedMethods(List.of(METHODS));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}