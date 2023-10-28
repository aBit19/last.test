package gr.abit.anbtest.http.runner;

import gr.abit.anbtest.contract.Runner;
import gr.abit.anbtest.contract.Assertion.TestResult;
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
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HttpRequestRunner implements Runner<HttpTest> {

  public HttpRequestRunner() {}

  @Override
  public List<TestResult> run(HttpTest httpTestStep) {
    try {
      HttpClient httpClient = getClient(httpTestStep);
      HttpRequest request = getRequest(httpTestStep);
      HttpResponse<String> send = httpClient.send(request, BodyHandlers.ofString());
      return verify(httpTestStep, send);
    } catch (IOException | InterruptedException e) {
      return List.of(TestResult.failed(e.getMessage()));
    }
  }

  private static List<TestResult> verify(HttpTest httpTestStep,
      HttpResponse<String> send) {
    return httpTestStep.getAssertions().stream().map(v -> v.verify(send))
        .collect(Collectors.toList());
  }

  private HttpClient getClient(HttpTest httpTestStep) {
    Builder builder = HttpClient
        .newBuilder()
        .connectTimeout(Duration.of(httpTestStep.getTimeout(), ChronoUnit.MILLIS))
        .followRedirects(Redirect.NORMAL);
    httpTestStep.getAuthorization().flatMap(HttpBasicAuth::getAuthenticator)
        .ifPresent(builder::authenticator);
    return builder.build();
  }

  private HttpRequest getRequest(HttpTest httpTestStep) {
    return HttpRequest.newBuilder(URI.create(httpTestStep.getDestination()))
        .GET()
        .build();
  }

}
