package gr.abit.last.test.plugins.http.runner;

import gr.abit.last.test.contract.Test;
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

  @Override
  public String getType() {
    return "http";
  }

  @Override
  public String getDescription() {
    return description;
  }

  public Optional<HttpBasicAuth> getAuthorization() {
    return Optional.ofNullable(authorization);
  }

}