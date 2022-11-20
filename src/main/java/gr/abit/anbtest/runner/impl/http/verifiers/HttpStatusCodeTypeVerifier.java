package gr.abit.anbtest.runner.impl.http.verifiers;

import gr.abit.anbtest.runner.contract.VerifierResult;
import gr.abit.anbtest.runner.impl.http.verifiers.HttpStatusCodeTypeVerifier.HttpStatusCodeVerifier;
import java.net.http.HttpResponse;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HttpStatusCodeTypeVerifier implements HttpVerifierType<HttpStatusCodeVerifier> {

  @Override
  public String getType() {
    return "statusCode";
  }

  @Override
  public HttpStatusCodeVerifier buildVerifier(Map<String, Map<String, String>> mapping) {
    Map<String, String> stringStringMap = mapping.get(getType());
    return new HttpStatusCodeVerifier(Integer.parseInt(stringStringMap.get("equals")));
  }

  static class HttpStatusCodeVerifier extends HttpVerifier {
    private final int expectedStatusCode;

    HttpStatusCodeVerifier(int expectedStatusCode) {
      this.expectedStatusCode = expectedStatusCode;
    }

    @Override
    public VerifierResult verify(HttpResponse<String> input) {
      VerifierResult verifierResult = new VerifierResult();

      boolean pass = input.statusCode() == expectedStatusCode;
      verifierResult.setSuccess(pass);

      String message = pass ? "OK" : String.format("Expected status %d, but got %d", expectedStatusCode, input.statusCode());
      verifierResult.setMessage(message);

      return verifierResult;

    }

  }

}
