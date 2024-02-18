package gr.abit.last.test.contract;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public interface Assertion<I> {
  TestResult verify(I input);


  @Setter
  @Getter
  @NoArgsConstructor
  class TestResult {

    private boolean success;
    private String message;
    public static TestResult failed(String message) {
      TestResult testResult = new TestResult();
      testResult.setSuccess(false);
      testResult.setMessage(message);
      return testResult;
    }

  }

}

