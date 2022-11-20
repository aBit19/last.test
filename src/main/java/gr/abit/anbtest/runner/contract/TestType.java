package gr.abit.anbtest.runner.contract;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import javax.inject.Inject;

public abstract class TestType<S extends TestStep, R extends Runner<?>, V extends VerifierType<?, ?, ?>> {

  @Inject
  ObjectMapper objectMapper;

  protected abstract String getType();

  protected abstract S toStep(String encodedObject);
  protected abstract R getRunner(String encodedDetails);

  protected abstract List<V> getVerifiers();

  protected <T1> T1 deserialize(Class<T1> cls, String encodedObject) {
    try {
      return objectMapper.readValue(encodedObject, cls);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean cahHandleType(String type) {
    return type.equals(this.getType());
  }


}
