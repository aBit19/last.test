package gr.abit.last.test.contract.testtype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;

@Getter
public class EnumType implements TestType {
  private final String type = "enum";
  private final String name;
  private final String description;
  private final List<TestType> values;

  private EnumType(String name, List<TestType> values, String desc) {
    this.name = name;
    this.values = values;
    this.description = desc;
  }

  public static EnumTestTypeBuilder getBuilder() {
    return new EnumTestTypeBuilder();
  }

  public static class EnumTestTypeBuilder {
    private String name;
    private String description;
    private final List<TestType> values = new ArrayList<>();

    private EnumTestTypeBuilder() {}

    public EnumTestTypeBuilder withFieldName(String name) {
      this.name = name;
      return this;
    }

    public EnumTestTypeBuilder withEnums(TestType value, TestType... values) {
      this.values.add(value);
      this.values.addAll(Arrays.asList(values));
      return this;
    }

    public EnumTestTypeBuilder withDescription(String description) {
      this.description = description;
      return this;
    }

    public EnumType build() {
      return new EnumType(name, values, description);
    }

  }

}
