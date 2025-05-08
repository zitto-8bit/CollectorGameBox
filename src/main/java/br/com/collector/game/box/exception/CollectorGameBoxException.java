package br.com.collector.game.box.exception;

public class CollectorGameBoxException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CollectorGameBoxException(String message) {
        super(message);
    }

    public CollectorGameBoxException(String message, Throwable cause) {
        super(message, cause);
    }
}