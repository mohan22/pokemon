package io.app.pokedexservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.app.pokedexservice.model.PokeDexResp;
import io.app.pokedexservice.service.PokemonService;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

  @Autowired
  PokemonService pokemonService;

  @RequestMapping(value = "/{name}", method = RequestMethod.GET)
  public PokeDexResp getPokemon(@PathVariable("name") String name) {
    return pokemonService.getBasicInfo(name);
  }

  @RequestMapping(value = "/translated/{name}", method = RequestMethod.GET)
  public PokeDexResp getTranslatedPokemon(@PathVariable("name") String name) {
    return pokemonService.getTranslatedInfo(name);
  }

}
