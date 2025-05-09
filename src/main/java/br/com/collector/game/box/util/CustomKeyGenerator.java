package br.com.collector.game.box.util;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import br.com.collector.game.box.dto.UsuarioDTO;

import java.lang.reflect.Method;

@Component("customKeyGenerator")
public class CustomKeyGenerator implements KeyGenerator {

	@Override
	public Object generate(Object target, Method method, Object... params) {
	    StringBuilder key = new StringBuilder();
	    key.append(target.getClass().getSimpleName())
	       .append("_")
	       .append(method.getName());

	    for (Object param : params) {
	        key.append("_");

	        if (param instanceof UsuarioDTO usuario && usuario.getId() != null) {
	            key.append(usuario.getId());
	        } else {
	            key.append(param);
	        }
	    }

	    return key.toString();
	}

}
