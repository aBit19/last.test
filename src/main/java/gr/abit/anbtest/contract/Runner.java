package gr.abit.anbtest.contract;

import gr.abit.anbtest.contract.Assertion.TestResult;
import java.util.List;

public interface Runner<T extends Test> {

  List<TestResult> run(T test);

}
