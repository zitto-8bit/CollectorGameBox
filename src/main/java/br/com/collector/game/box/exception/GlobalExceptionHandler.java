package br.com.collector.game.box.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

/**
 * Handler global para exceções na aplicação.
 */
@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Captura exceções não tratadas.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
		Map<String, Object> response = new HashMap<>();
		response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.put("message", ex.getMessage());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
	
	/**
	 * Trata erros de validação de campos (Bean Validation).
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();

	    ex.getBindingResult().getFieldErrors()
	            .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

	    Map<String, Object> response = Map.of(
	        "status", HttpStatus.BAD_REQUEST.value(),
	        "erros", errors
	    );

	    Class<?> targetClass = ex.getBindingResult().getTarget() != null
	            ? ex.getBindingResult().getTarget().getClass()
	            : Object.class;

	    log.warn("Erros de validação na classe {}: {}", targetClass.getSimpleName(), errors);

	    return ResponseEntity.badRequest().body(response);
	}
	
	@ExceptionHandler(CollectorGameBoxException.class)
    public ResponseEntity<CollectorGameBoxException> handleCollectorGameBoxException(CollectorGameBoxException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex);
    }
}
