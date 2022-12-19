package gr.abit.anbtest.contract;

import java.util.List;

public interface Runner<T extends TestStep> {

  List<VerifierResult> run(T testStep);

}
