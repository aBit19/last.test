package gr.abit.last.test.plugins.http.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.abit.last.test.contract.Assertion.TestResult;
import gr.abit.last.test.plugins.http.runner.HttpRequestRunner;
import gr.abit.last.test.plugins.http.runner.HttpTest;
import gr.abit.last.test.rest.TestRunnerRest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class HttpTestRunnerRest implements TestRunnerRest {

  @Inject
  HttpRequestRunner httpRequestRunner;
  @Inject
  ObjectMapper objectMapper;

  @Override
  public String getTestCode() {
    return "http";
  }

  @Override
  public List<TestResult> runTest(String test) {
    try {
      HttpTest httpTest = objectMapper.readValue(test, HttpTest.class);
      return httpRequestRunner.run(httpTest);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
