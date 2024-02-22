package gr.abit.last.test.store;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gr.abit.last.test.contract.Test;
import java.net.URI;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestDetails implements Test {
  String id;
  String type;
  String name;
  String description;
  URI url;
}
