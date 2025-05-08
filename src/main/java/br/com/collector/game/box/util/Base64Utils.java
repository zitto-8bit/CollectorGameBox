package br.com.collector.game.box.util;

import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class Base64Utils {

	public String encodeImg(byte[] img) {
		return Base64.getEncoder().encodeToString(img);
	}
	
	public byte[] decodeImg(String img) {
		return Base64.getDecoder().decode(img);
	}
	
	public byte[] decodeImgWithPrefix(String imgWithPrefix) {
	    if (imgWithPrefix.startsWith("data:image/png;base64,")) {
	    	imgWithPrefix = imgWithPrefix.substring("data:image/png;base64,".length());
	    }

	    return Base64.getDecoder().decode(imgWithPrefix);
	}
}
