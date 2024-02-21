package gr.abit.last.test.plugins.http.runner;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.http.HttpResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HttpStatusCodeAssertion implements HttpAssertion {

  @JsonProperty("expected_status_code")
  private int expectedStatusCode;

  @Override
  public TestResult verify(HttpResponse<String> input) {
    TestResult verifierResult = new TestResult();

    boolean pass = input.statusCode() == expectedStatusCode;
    verifierResult.setSuccess(pass);

    String message = String.format("Expected status %d, got %d", expectedStatusCode, input.statusCode());
    verifierResult.setMessage(message);

    return verifierResult;

  }

 }
