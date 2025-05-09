package br.com.collector.game.box.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.collector.game.box.util.CustomKeyGenerator;

@EnableCaching
@Configuration
public class CacheConfig {
	
	@Bean
    KeyGenerator keyGenerator() {
        return new CustomKeyGenerator();
    }
	
}
