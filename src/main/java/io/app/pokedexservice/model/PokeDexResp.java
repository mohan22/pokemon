package io.app.pokedexservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public class PokeDexResp implements Serializable {
  @JsonProperty("name")
  private String name;
  @JsonProperty("description")
  private String description;
  @JsonProperty("habitat")
  private String habitat;
  @JsonProperty("isLegendary")
  private boolean isLegendary;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getHabitat() {
    return habitat;
  }

  public void setHabitat(String habitat) {
    this.habitat = habitat;
  }

  public boolean getIsLegendary() {
    return isLegendary;
  }

  public void setIsLegendary(boolean legendary) {
    isLegendary = legendary;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
