package gr.abit.anbtest.contract.testtype;

import lombok.Getter;

@Getter
public class ListType implements TestType {
  private final String type = "list";
  private final TestType listType;
  private final String description;
  private final String name;

  private ListType(String name, TestType elements, String description) {
    this.listType = elements;
    this.name = name;
    this.description = description;
  }

  public static ListTypeBuilder getBuilder() {
    return new ListTypeBuilder();
  }

  public static class ListTypeBuilder {
    private TestType listType;
    private String description;
    private String name;

    private ListTypeBuilder() {}

    public ListTypeBuilder withFieldName(String name) {
      this.name = name;
      return this;
    }

    public ListTypeBuilder withDescription(String description) {
      this.description = description;
      return this;
    }

    public ListTypeBuilder ofType(TestType testType) {
      this.listType = testType;
      return this;
    }

    public ListType build() {
      return new ListType(name, listType, description);
    }

  }


}
