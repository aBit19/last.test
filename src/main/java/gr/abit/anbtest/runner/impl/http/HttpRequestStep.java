package gr.abit.anbtest.runner.impl.http;

import gr.abit.anbtest.runner.contract.TestStep;
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
  long timeout;
  String destination;
  HttpRequestStepAuth authorization;
  List<Map<String, Map<String, String>>> verifiers;

}
