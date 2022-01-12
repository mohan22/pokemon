package io.app.pokedexservice.component.translator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.app.pokedexservice.model.Translation;
import io.app.pokedexservice.util.TranslationUtil;

@Component
public class ShakespeareTrans implements Translator {
  @Autowired
  public RestTemplate restTemplate;
  String translatorUrl = "https://api.funtranslations.com/translate/shakespeare.json";

  public Translation translate(String text) {
    return TranslationUtil.getTranslationResult(translatorUrl, text, restTemplate);
  }
}
