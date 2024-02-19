package gr.abit.last.test.contract;

import gr.abit.last.test.contract.testtype.TestType;

public interface TestSchema {
  String getName();
  String getDescription();
  TestType getDefinition();

}
