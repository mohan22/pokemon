package io.app.pokedexservice;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import io.app.pokedexservice.component.TranslatorFactory;
import io.app.pokedexservice.model.FlavourTextEntry;
import io.app.pokedexservice.model.Habitat;
import io.app.pokedexservice.model.Language;
import io.app.pokedexservice.model.PokeDexResp;
import io.app.pokedexservice.model.Pokemon;
import io.app.pokedexservice.service.impl.PokemonServiceImpl;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PokemonServiceImplTest {
  @InjectMocks
  PokemonServiceImpl pokemonService;
  @Mock
  RestTemplate restTemplate;
  @Mock
  TranslatorFactory translatorFactory;

  Pokemon pokemon = new Pokemon();

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
    flavourTextEntry.setDesription("test description");
    List<FlavourTextEntry> flavourTextEntryList = new ArrayList<>();
    flavourTextEntryList.add(flavourTextEntry);
    pokemon.setFlavourTextEntries(flavourTextEntryList);
  }

  @Test
  public void getBasicInfoTestWhenEnglishDescriptionIsFound() {
    Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(pokemon);
    PokeDexResp pokeDexResp = pokemonService.getBasicInfo("mewtwo");
    Assert.assertTrue(pokeDexResp.getIsLegendary());
    Assert.assertEquals("test description",pokeDexResp.getDescription());
  }

  @Test
  public void getBasicInfoTestWhenEnglishDescriptionIsNotFound() {
    pokemon.getFlavourTextEntries().get(0).getLanguage().setName("fr");
    Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(pokemon);
    PokeDexResp pokeDexResp = pokemonService.getBasicInfo("mewtwo");
    Assert.assertTrue(pokeDexResp.getIsLegendary());
    Assert.assertNull(pokeDexResp.getDescription());
  }
}
