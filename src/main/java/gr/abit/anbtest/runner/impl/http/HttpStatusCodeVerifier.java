package gr.abit.anbtest.runner.impl.http;

import gr.abit.anbtest.runner.contract.VerifierResult;
import java.net.http.HttpResponse;
import javax.enterprise.context.ApplicationScoped;

class HttpStatusCodeVerifier extends HttpVerifier {
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
