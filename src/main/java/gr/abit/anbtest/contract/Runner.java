package gr.abit.anbtest.contract;

import java.util.List;

public interface Runner<T extends Test> {

  List<VerifierResult> run(T testStep);

}
