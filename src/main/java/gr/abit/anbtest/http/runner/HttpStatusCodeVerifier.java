package gr.abit.anbtest.http.runner;

import gr.abit.anbtest.contract.VerifierResult;
import java.net.http.HttpResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HttpStatusCodeVerifier implements HttpVerifier {

  private int expectedStatusCode;

  @Override
  public VerifierResult verify(HttpResponse<String> input) {
    VerifierResult verifierResult = new VerifierResult();

    boolean pass = input.statusCode() == expectedStatusCode;
    verifierResult.setSuccess(pass);

    String message = String.format("Expected status %d, got %d", expectedStatusCode, input.statusCode());
    verifierResult.setMessage(message);

    return verifierResult;

  }

}
