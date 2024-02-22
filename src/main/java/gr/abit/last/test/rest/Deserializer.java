package gr.abit.last.test.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class Deserializer {

  private final ObjectMapper objectMapper;
  @Inject
  public Deserializer(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public <T> T get(String payload, Class<T> type) {
    try {
      return objectMapper.readValue(payload, type);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
