package io.app.pokedexservice.component;

import io.app.pokedexservice.component.translator.Translator;
import io.app.pokedexservice.model.PokeDexResp;

public interface TranslatorFactory {
  public Translator getTranslator(PokeDexResp pokeDexResp);
}
