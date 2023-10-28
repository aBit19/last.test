package gr.abit.anbtest.contract;

import gr.abit.anbtest.contract.Verifier.VerifierResult;
import java.util.List;

public interface Runner<T extends Test> {

  List<VerifierResult> run(T testStep);

}
