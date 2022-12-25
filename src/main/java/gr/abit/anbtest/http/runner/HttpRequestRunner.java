package gr.abit.anbtest.http.runner;

import gr.abit.anbtest.contract.Runner;
import gr.abit.anbtest.contract.VerifierResult;
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
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HttpRequestRunner implements Runner<HttpRequestStep> {

  public HttpRequestRunner() {}

  @Override
  public List<VerifierResult> run(HttpRequestStep httpRequestStep) {
    try {
      HttpClient httpClient = getClient(httpRequestStep);
      HttpRequest request = getRequest(httpRequestStep);
      HttpResponse<String> send = httpClient.send(request, BodyHandlers.ofString());
      return verify(httpRequestStep, send);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private static List<VerifierResult> verify(HttpRequestStep httpRequestStep,
      HttpResponse<String> send) {
    return httpRequestStep.getAssertions().stream().map(v -> v.verify(send))
        .collect(Collectors.toList());
  }

  private HttpClient getClient(HttpRequestStep httpRequestStep) {
    Builder builder = HttpClient
        .newBuilder()
        .connectTimeout(Duration.of(httpRequestStep.getTimeout(), ChronoUnit.MILLIS))
        .followRedirects(Redirect.NORMAL);
    httpRequestStep.getAuthorization().getAuthenticator().ifPresent(builder::authenticator);
    return builder.build();
  }

  private HttpRequest getRequest(HttpRequestStep httpRequestStep) {
    return HttpRequest.newBuilder(URI.create(httpRequestStep.getDestination()))
        .GET()
        .build();
  }

}
