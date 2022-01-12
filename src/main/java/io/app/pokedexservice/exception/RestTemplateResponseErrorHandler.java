package io.app.pokedexservice.exception;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
  @Override
  public boolean hasError(ClientHttpResponse response) throws IOException {
    return (
        response.getStatusCode().series() == CLIENT_ERROR
            || response.getStatusCode().series() == SERVER_ERROR);
  }

  @Override
  public void handleError(ClientHttpResponse response) throws IOException {
    if (response.getStatusCode()
        .series() == HttpStatus.Series.SERVER_ERROR) {
      throw new TranslationServideException("Service Error");
    } else if (response.getStatusCode()
        .series() == HttpStatus.Series.CLIENT_ERROR) {
      throw new BadInputException("Resource not found");
    }
    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
      throw new NotFoundException("Resource not found");
    }
  }
}
