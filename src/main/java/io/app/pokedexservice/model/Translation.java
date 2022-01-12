package io.app.pokedexservice.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Translation {
  @JsonProperty("success")
  private Success success;
  @JsonProperty("contents")
  private TranslatedContent translatedContent;


  public Success getSuccess() {
    return success;
  }

  public void setSuccess(Success success) {
    this.success = success;
  }

  public TranslatedContent getTranslatedContent() {
    return translatedContent;
  }

  public void setTranslatedContent(TranslatedContent translatedContent) {
    this.translatedContent = translatedContent;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
