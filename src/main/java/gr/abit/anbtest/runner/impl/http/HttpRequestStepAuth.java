package gr.abit.anbtest.runner.impl.http;

import java.net.Authenticator;
import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HttpRequestStepAuth {

  private String type;
  private String username;
  private String password;

  public Optional<Authenticator> getAuthenticator() {
    return Optional.ofNullable(null);
  }

}
