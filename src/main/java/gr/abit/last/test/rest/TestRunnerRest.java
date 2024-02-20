package gr.abit.last.test.rest;

import gr.abit.last.test.contract.Assertion.TestResult;
import java.util.List;

public interface TestRunnerRest {
  String getTestCode();
  List<TestResult> runTest(String test);
}
