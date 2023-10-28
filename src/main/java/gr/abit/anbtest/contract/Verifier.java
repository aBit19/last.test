package gr.abit.anbtest.contract;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public interface Verifier<I> {
  VerifierResult verify(I input);


  @Setter
  @Getter
  @NoArgsConstructor
  class VerifierResult {

    private boolean success;
    private String message;

  }

}

