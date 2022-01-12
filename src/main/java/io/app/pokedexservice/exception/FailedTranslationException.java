package io.app.pokedexservice.exception;

public class FailedTranslationException extends RuntimeException {
  public FailedTranslationException(String msg) {
    super(msg);
  }
}
