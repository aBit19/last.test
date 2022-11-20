package gr.abit.anbtest.runner.contract;


import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface VerifierType<I, VI, V extends Verifier<VI>> {

  default boolean canHandle(String type) {
    return type.equals(getType());
  }
  String getType();

  V buildVerifier(I object);

}
