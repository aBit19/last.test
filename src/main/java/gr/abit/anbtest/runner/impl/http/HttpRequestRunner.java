package gr.abit.anbtest.runner.impl.http;

import gr.abit.anbtest.runner.contract.Runner;
import gr.abit.anbtest.runner.contract.VerifierResult;
import gr.abit.anbtest.runner.impl.http.verifiers.HttpVerifier;
import gr.abit.anbtest.runner.impl.http.verifiers.HttpVerifierType;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Builder;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

public class HttpRequestRunner implements Runner<List<Map<String, Map<String, String>>>> {

  private final List<HttpVerifierType<?>> httpVerifiers;
  private final HttpRequestStep httpRequestStep;

  HttpRequestRunner(HttpRequestStep httpRequestStep, List<HttpVerifierType<?>> verifierTypes) {
    this.httpRequestStep = httpRequestStep;
    this.httpVerifiers = verifierTypes;
  }

  @Override
  public List<Map<String, Map<String, String>>> run() {
    try {
      HttpClient httpClient = getClient();
      HttpRequest request = getRequest();
      HttpResponse<String> send = httpClient.send(request, BodyHandlers.ofString());
      for (Map<String, Map<String, String>> verifierDetails : httpRequestStep.getVerifiers()) {
        String verifierType = verifierDetails.keySet().iterator().next();
        Map<String, String> details = verifierDetails.get(verifierType);
        for (HttpVerifierType<?> httpVerifierType : httpVerifiers) {
          boolean canHandle = httpVerifierType.canHandle(verifierType);
          if (canHandle) {
            HttpVerifier httpVerifier = httpVerifierType.buildVerifier(verifierDetails);
            VerifierResult verify = httpVerifier.verify(send);
            details.put("message", verify.getMessage());
            details.put("success", String.valueOf(verify.isSuccess()));
          }
        }
      }
      return httpRequestStep.getVerifiers();
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private HttpClient getClient() {
    Builder builder = HttpClient
        .newBuilder()
        .connectTimeout(Duration.of(httpRequestStep.getTimeout(), ChronoUnit.MILLIS))
        .followRedirects(Redirect.NORMAL);
    httpRequestStep.getAuthorization().getAuthenticator().ifPresent(builder::authenticator);
    return builder.build();
  }

  private HttpRequest getRequest() {
    return HttpRequest.newBuilder(URI.create(httpRequestStep.getDestination()))
        .GET()
        .build();
  }

}
