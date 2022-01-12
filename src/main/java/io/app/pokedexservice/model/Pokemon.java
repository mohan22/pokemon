package io.app.pokedexservice.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Pokemon implements Serializable {

  @JsonProperty("name")
  private String name;
  @JsonProperty("flavor_text_entries")
  private List<FlavourTextEntry> flavourTextEntries;
  @JsonProperty("habitat")
  private Habitat habitat;
  @JsonProperty("is_legendary")
  private boolean isLegendary;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<FlavourTextEntry> getFlavourTextEntries() {
    return flavourTextEntries;
  }

  public void setFlavourTextEntries(List<FlavourTextEntry> flavourTextEntries) {
    this.flavourTextEntries = flavourTextEntries;
  }

  public Habitat getHabitat() {
    return habitat;
  }

  public void setHabitat(Habitat habitat) {
    this.habitat = habitat;
  }

  public boolean isLegendary() {
    return isLegendary;
  }

  public void setLegendary(boolean legendary) {
    isLegendary = legendary;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
