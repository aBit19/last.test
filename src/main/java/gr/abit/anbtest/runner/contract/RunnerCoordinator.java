package gr.abit.anbtest.runner.contract;

import io.quarkus.arc.All;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RunnerCoordinator {

  @Inject
  @All
  List<TestType<?, ?, ?>> types;

  public Object handleTest(String type, String testDetails) {
    TestType<?, ?, ?> testType = types.stream()
        .filter(test-> test.cahHandleType(type))
        .findFirst()
        .orElseThrow(RuntimeException::new);

    Runner<?> runner = testType.getRunner(testDetails);
    return runner.run();
  }

}
