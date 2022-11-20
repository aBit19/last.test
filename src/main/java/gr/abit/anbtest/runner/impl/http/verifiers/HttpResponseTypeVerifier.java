package gr.abit.anbtest.runner.impl.http.verifiers;

import gr.abit.anbtest.runner.contract.VerifierResult;
import gr.abit.anbtest.runner.impl.http.verifiers.HttpResponseTypeVerifier.HttpResponseVerifier;
import java.net.http.HttpResponse;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HttpResponseTypeVerifier implements HttpVerifierType<HttpResponseVerifier> {

  @Override
  public String getType() {
    return "responseBody";
  }

  @Override
  public HttpResponseVerifier buildVerifier(Map<String, Map<String, String>> mapping ) {
    Map<String, String> stringStringMap = mapping.get(getType());
    return new HttpResponseVerifier(stringStringMap.get("equals"));
  }

  static class HttpResponseVerifier extends HttpVerifier {

    private final String expectedBody;

    HttpResponseVerifier(String expectedBody) {
      this.expectedBody = expectedBody;
    }

    @Override
    public VerifierResult verify(HttpResponse<String> input) {
      VerifierResult verifierResult = new VerifierResult();

      boolean pass = input.body().equals(expectedBody);
      verifierResult.setSuccess(pass);

      String message =
          pass ? "OK" : String.format("Expected body %s, but got %s", expectedBody, input.body());
      verifierResult.setMessage(message);

      return verifierResult;
    }

  }

}