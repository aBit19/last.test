package gr.abit.last.test.rest.schema;

import lombok.Getter;

@Getter
public class StringType implements TestType {
  private final String type = "string";
  private final String name;
  private final String description;

  private StringType(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public static StringType withNameAndDescription(String name, String description) {
    return new StringType(name, description);
  }

  public static StringType withName(String name) {
    return new StringType(name, null);
  }

}
