package io.app.pokedexservice.component.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.app.pokedexservice.component.TranslatorFactory;
import io.app.pokedexservice.component.translator.ShakespeareTrans;
import io.app.pokedexservice.component.translator.Translator;
import io.app.pokedexservice.component.translator.YodaTrans;
import io.app.pokedexservice.model.PokeDexResp;

@Component
public class TranslatorFactoryImpl implements TranslatorFactory {
  @Autowired
  public YodaTrans yodaTrans;

  @Autowired
  public ShakespeareTrans shakespeareTrans;

  @Override
  public Translator getTranslator(PokeDexResp pokeDexResp) {
    if ("cave".equalsIgnoreCase(pokeDexResp.getHabitat()) || pokeDexResp.getIsLegendary()) {
      return yodaTrans;
    }
    return shakespeareTrans;
  }
}
