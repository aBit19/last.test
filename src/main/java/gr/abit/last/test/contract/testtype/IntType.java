package gr.abit.last.test.contract.testtype;

import lombok.Getter;

@Getter
public class IntType implements TestType {

  private final String type = "int";
  private final String name;
  private final String description;

  private IntType(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public static IntType withName(String name) {
    return new IntType(name, null);
  }

  public static IntType withNameAndDescription(String name, String description) {
    return new IntType(name, description);
  }

}
