package gr.abit.last.test.plugins.http.runner;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.http.HttpResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HttpResponseAssertion implements HttpAssertion {

  @JsonProperty( "expected_body")
  private String expectedBody;

  @Override
  public TestResult verify(HttpResponse<String> input) {
    TestResult verifierResult = new TestResult();

    boolean pass = input.body().equals(expectedBody);
    verifierResult.setSuccess(pass);

    String message = String.format("Expected body %s, got %s", expectedBody, input.body());
    verifierResult.setMessage(message);

    return verifierResult;
  }


}
