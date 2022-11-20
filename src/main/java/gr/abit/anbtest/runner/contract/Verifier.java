package gr.abit.anbtest.runner.contract;

public interface Verifier<I> {
  VerifierResult verify(I input);

}
