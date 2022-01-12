package io.app.pokedexservice;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import io.app.pokedexservice.component.impl.TranslatorFactoryImpl;
import io.app.pokedexservice.component.translator.YodaTrans;
import io.app.pokedexservice.model.FlavourTextEntry;
import io.app.pokedexservice.model.Habitat;
import io.app.pokedexservice.model.Language;
import io.app.pokedexservice.model.PokeDexResp;
import io.app.pokedexservice.model.Pokemon;
import io.app.pokedexservice.model.Success;
import io.app.pokedexservice.model.TranslatedContent;
import io.app.pokedexservice.model.Translation;
import io.app.pokedexservice.service.impl.PokemonServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class PokemonServiceImplTest {
  @InjectMocks
  PokemonServiceImpl pokemonService;
  @Mock
  RestTemplate restTemplate;
  @Mock
  TranslatorFactoryImpl translatorFactory;

  @Mock
  YodaTrans yodaTrans;

  Pokemon pokemon = new Pokemon();
  Translation translation = new Translation();
  String yodaTranslation =
      "Testing translation,  we are";
  String shakespearTranslation =
      "We art testing translation";

  /**
   * Setup.
   */
  @Before
  public void setup() {
    MockitoAnnotations.openMocks(this);
    pokemon.setName("mewtwo");
    pokemon.setLegendary(true);
    Habitat habitat = new Habitat();
    habitat.setName("cave");
    habitat.setUrl("url");
    pokemon.setHabitat(habitat);
    FlavourTextEntry flavourTextEntry = new FlavourTextEntry();
    Language language = new Language();
    language.setName("en");
    language.setUrl("url");
    flavourTextEntry.setLanguage(language);
    flavourTextEntry.setDesription("we are testing translation");
    List<FlavourTextEntry> flavourTextEntryList = new ArrayList<>();
    flavourTextEntryList.add(flavourTextEntry);
    pokemon.setFlavourTextEntries(flavourTextEntryList);

    translation.setSuccess(new Success());
    TranslatedContent translatedContent = new TranslatedContent();
    translatedContent.setTranslated(yodaTranslation);
    translation.setTranslatedContent(translatedContent);
  }

  @Test
  public void getBasicInfoTestWhenEnglishDescriptionIsFound() {
    Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(pokemon);
    PokeDexResp pokeDexResp = pokemonService.getBasicInfo("mewtwo");
    Assert.assertTrue(pokeDexResp.getIsLegendary());
    Assert.assertEquals("we are testing translation", pokeDexResp.getDescription());
  }

  @Test
  public void getBasicInfoTestWhenEnglishDescriptionIsNotFound() {
    pokemon.getFlavourTextEntries().get(0).getLanguage().setName("fr");
    Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(pokemon);
    PokeDexResp pokeDexResp = pokemonService.getBasicInfo("mewtwo");
    Assert.assertTrue(pokeDexResp.getIsLegendary());
    Assert.assertNull(pokeDexResp.getDescription());
    pokemon.getFlavourTextEntries().get(0).getLanguage().setName("en");
  }

  @Test
  public void getYodaTranlated() {
    Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(pokemon);
    PokeDexResp pokeDexResp = pokemonService.getBasicInfo("mewtwo");
    Mockito.when(translatorFactory.getTranslator(Mockito.any())).thenReturn(yodaTrans);

    Mockito.when(yodaTrans.translate(Mockito.anyString())).thenReturn(translation);
    pokeDexResp = pokemonService.getTranslatedInfo("mewtwo");
    Assert.assertEquals(pokeDexResp.getDescription(), yodaTranslation);
    verify(yodaTrans, times(1)).translate(Mockito.anyString());
  }

  //TODO: Addtional tests can be added for more code coverage for production
}
