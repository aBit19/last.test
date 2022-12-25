package gr.abit.anbtest.http.runner;

import gr.abit.anbtest.contract.testtype.StringType;
import gr.abit.anbtest.contract.testtype.TestType;
import java.net.Authenticator;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HttpBasicAuth {

  private String username;
  private String password;

  public Optional<Authenticator> getAuthenticator() {
    return Optional.ofNullable(null);
  }


  public static List<TestType> getTestSchema() {
    return List.of(
        StringType.withName("username"),
        StringType.withName("password")
    );
  }
}
