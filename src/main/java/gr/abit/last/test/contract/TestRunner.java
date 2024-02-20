package gr.abit.last.test.contract;

import gr.abit.last.test.contract.Assertion.TestResult;
import java.util.List;

public interface TestRunner<T extends Test> {

  List<TestResult> run(T test);

}
