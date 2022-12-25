package gr.abit.anbtest.http.runner;

import gr.abit.anbtest.contract.VerifierResult;
import gr.abit.anbtest.contract.testtype.ObjectType;
import gr.abit.anbtest.contract.testtype.StringType;
import gr.abit.anbtest.contract.testtype.TestType;
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

  static TestType getTestType() {
    return ObjectType.getBuilder().withFields(
        StringType.withNameAndDescription("expectedBody", "The expected body")).build();
  }
}
