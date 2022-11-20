package gr.abit.anbtest.runner.impl.http;

import java.util.Map;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HttpStatusCodeTypeVerifier implements HttpVerifierType<HttpStatusCodeVerifier> {


  @Override
  public String getType() {
    return "httpStatusCode";
  }

  @Override
  public HttpStatusCodeVerifier buildVerifier(Map<String, Map<String, String>> mapping) {
    Map<String, String> stringStringMap = mapping.get(getType());
    return new HttpStatusCodeVerifier(Integer.parseInt(stringStringMap.get("equals")));
  }

}
