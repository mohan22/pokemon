package io.app.pokedexservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.app.pokedexservice.component.TranslatorFactory;
import io.app.pokedexservice.component.translator.Translator;
import io.app.pokedexservice.model.FlavourTextEntry;
import io.app.pokedexservice.model.Habitat;
import io.app.pokedexservice.model.PokeDexResp;
import io.app.pokedexservice.model.Pokemon;
import io.app.pokedexservice.model.Translation;
import io.app.pokedexservice.service.PokemonService;

import java.util.List;

@Service
public class PokemonServiceImpl implements PokemonService {
  @Autowired
  public RestTemplate restTemplate;

  @Autowired
  public TranslatorFactory translatorFactory;

  @Override
  public PokeDexResp getBasicInfo(String pokemon) {
    Pokemon pokemonResp = getPokemon(pokemon);
    PokeDexResp pokeDexResp = new PokeDexResp();
    if (pokemonResp == null) {
      return pokeDexResp;
    }

    List<FlavourTextEntry> flavourTextEntries = pokemonResp.getFlavourTextEntries();
    FlavourTextEntry flavourTextEntry = new FlavourTextEntry();
    //Since there may be multiple descriptions available, we are taking first English description value
    if (flavourTextEntries != null && !flavourTextEntries.isEmpty()) {
      flavourTextEntry = flavourTextEntries.stream()
          .filter(flavourTextEntry1 ->
              "en".equalsIgnoreCase(flavourTextEntry1.getLanguage().getName()))
          .findFirst()
          .orElse(new FlavourTextEntry());
    }


    pokeDexResp.setDescription(flavourTextEntry.getDesription());
    pokeDexResp.setIsLegendary(pokemonResp.isLegendary());
    Habitat habitat = pokemonResp.getHabitat();
    String habitatName = pokemonResp.getHabitat() == null ? null : habitat.getName();
    pokeDexResp.setHabitat(habitatName);
    pokeDexResp.setName(pokemonResp.getName());
    return pokeDexResp;
  }

  protected Pokemon getPokemon(String pokemon) {
    return restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon-species/" + pokemon, Pokemon.class);
  }

  @Override
  public PokeDexResp getTranslatedInfo(String pokemon) {
    PokeDexResp pokeDexResp = getBasicInfo(pokemon);
    Translator translator = translatorFactory.getTranslator(pokeDexResp);
    Translation translation = translator.translate(pokeDexResp.getDescription());
    pokeDexResp.setDescription(translation.getTranslatedContent().getTranslated());
    return pokeDexResp;
  }
}
