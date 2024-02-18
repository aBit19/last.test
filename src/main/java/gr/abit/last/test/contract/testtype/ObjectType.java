package gr.abit.last.test.contract.testtype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;

@Getter
public class ObjectType implements TestType {

  private final String type = "object";
  private final String name;
  private final List<TestType> fields;
  private final String description;

  private ObjectType(String name, List<TestType> fields, String description) {
    this.name = name;
    this.fields = fields;
    this.description = description;
  }

  public static ObjectTypeBuilder getBuilder() {
    return new ObjectTypeBuilder();
  }

  public static class ObjectTypeBuilder {
    private String key;
    private final List<TestType> fields = new ArrayList<>();
    private String description;

    public ObjectTypeBuilder withName(String key) {
      this.key = key;
      return this;
    }

    public ObjectTypeBuilder withFields(TestType type1, TestType... types) {
      fields.add(type1);
      fields.addAll(Arrays.asList(types));
      return this;
    }
    public ObjectTypeBuilder withFields(List<TestType> types) {
      fields.addAll(types);
      return this;
    }

    public ObjectTypeBuilder withDescription(String description) {
      this.description = description;
      return this;
    }

    public ObjectType build() {
      return new ObjectType(key, fields, description);
    }

  }

}
