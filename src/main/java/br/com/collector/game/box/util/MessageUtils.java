package br.com.collector.game.box.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageUtils {

	@Autowired
    private MessageSource messageSource;
	
	public String getMessage(String mensagem) {
		return messageSource.getMessage(mensagem, null, LocaleContextHolder.getLocale());
	}
	
}
