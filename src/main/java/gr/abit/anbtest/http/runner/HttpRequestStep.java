package gr.abit.anbtest.http.runner;

import gr.abit.anbtest.contract.TestStep;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HttpRequestStep implements TestStep {

  String id;
  String method;
  String body;
  long timeout;
  Map<String, String> headers;
  String destination;
  HttpRequestStepAuth authorization;
  List<HttpVerifier> verifiers;

}
