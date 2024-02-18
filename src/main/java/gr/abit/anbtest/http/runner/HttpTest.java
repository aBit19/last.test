package gr.abit.anbtest.http.runner;

import gr.abit.anbtest.contract.Test;
import gr.abit.anbtest.contract.testtype.TestSchema;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HttpTest implements Test {

  String id;
  String name;
  String description;
  String method;
  String body;
  long timeout;
  Map<String, String> headers;
  String destination;
  HttpBasicAuth authorization;
  List<HttpAssertion> assertions;

  @Override
  public String getName() {
    return name;
  }

  public Optional<HttpBasicAuth> getAuthorization() {
    return Optional.ofNullable(authorization);
  }

}