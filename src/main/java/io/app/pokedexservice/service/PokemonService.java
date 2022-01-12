package io.app.pokedexservice.service;

import io.app.pokedexservice.model.PokeDexResp;

public interface PokemonService {

  public PokeDexResp getBasicInfo(String pokemon);

  public PokeDexResp getTranslatedInfo(String pokemon);
}
