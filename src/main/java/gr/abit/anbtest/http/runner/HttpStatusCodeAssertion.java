package gr.abit.anbtest.http.runner;

import com.fasterxml.jackson.annotation.JsonProperty;
import gr.abit.anbtest.contract.VerifierResult;
import gr.abit.anbtest.contract.testtype.IntType;
import gr.abit.anbtest.contract.testtype.ObjectType;
import gr.abit.anbtest.contract.testtype.TestType;
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

  @JsonProperty( "expected_status_code")
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

  static TestType getTestType() {
    return ObjectType.getBuilder().withFields(
        IntType.withNameAndDescription("expectedStatusCode", "The expected status code")).build();
  }

}
