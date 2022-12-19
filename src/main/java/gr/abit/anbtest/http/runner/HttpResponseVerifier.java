package gr.abit.anbtest.http.runner;

import gr.abit.anbtest.contract.VerifierResult;
import java.net.http.HttpResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HttpResponseVerifier implements HttpVerifier {
  private String expectedBody;

  @Override
  public VerifierResult verify(HttpResponse<String> input) {
    VerifierResult verifierResult = new VerifierResult();

    boolean pass = input.body().equals(expectedBody);
    verifierResult.setSuccess(pass);

    String message = String.format("Expected body %s, got %s", expectedBody, input.body());
    verifierResult.setMessage(message);

    return verifierResult;
  }

}
