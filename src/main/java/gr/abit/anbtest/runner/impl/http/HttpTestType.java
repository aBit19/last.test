package gr.abit.anbtest.runner.impl.http;

import gr.abit.anbtest.runner.contract.TestType;
import io.quarkus.arc.All;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class HttpTestType extends TestType<HttpRequestStep, HttpRequestRunner, HttpVerifierType<?>> {

  @All
  @Inject
  List<HttpVerifierType<?>> httpVerifierTypes;

  @Override
  protected HttpRequestStep toStep(String encodedObject) {
    return deserialize(HttpRequestStep.class, encodedObject);
  }

  @Override
  protected HttpRequestRunner getRunner(String encodedObject) {
    HttpRequestStep httpRequestStep = toStep(encodedObject);
    return new HttpRequestRunner(httpRequestStep, httpVerifierTypes);
  }

  @Override
  protected List<HttpVerifierType<?>> getVerifiers() {
    return httpVerifierTypes;
  }

  @Override
  protected String getType() {
    return "http-client";
  }

}
