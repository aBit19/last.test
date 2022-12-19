package gr.abit.anbtest.contract;

public interface Verifier<I> {
  VerifierResult verify(I input);

}
