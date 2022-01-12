package io.app.pokedexservice.exception;

public class BadInputException extends RuntimeException{
  public BadInputException(String msg){
    super(msg);
  }
}
