package gr.abit.anbtest.runner.contract;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class VerifierResult {

  private boolean success;
  private String message;

}
