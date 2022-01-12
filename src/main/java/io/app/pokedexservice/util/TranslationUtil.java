package io.app.pokedexservice.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import io.app.pokedexservice.exception.FailedTranslationException;
import io.app.pokedexservice.model.TranslatedContent;
import io.app.pokedexservice.model.Translation;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TranslationUtil {
  private static final Logger logger = Logger.getLogger(TranslationUtil.class.getName());

  private TranslationUtil() {
  }

  /**
   * Makes API call to translation APIs
   *
   * @param url Url endpoint of translation API
   * @param string Text to be translated
   * @param restTemplate Restclient instance
   * @return {@link Translation}
   */
  public static Translation getTranslationResult(String url, String string, RestTemplate restTemplate) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("text", string);
    HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
    Translation translationResp;
    try {
      ResponseEntity<Translation> translation = restTemplate.exchange(url, HttpMethod.POST, entity, Translation.class);
      translationResp = translation.getBody();
      if (translationResp == null || translationResp.getSuccess().getTotal() < 1) {
        throw new FailedTranslationException("API could not translate");
      }
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Translation failed ::", ex);
      translationResp = new Translation();
      TranslatedContent translatedContent = new TranslatedContent();
      translatedContent.setTranslated("Translation is not available for this at the moment");
      translationResp.setTranslatedContent(translatedContent);
    }
    return translationResp;
  }
}
