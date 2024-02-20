package gr.abit.last.test.rest.schema;

import lombok.Getter;

@Getter
public class MapType implements TestType {
  private final String type = "map";
  private final String name;
  private final String description;

  private MapType(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public static MapType withName(String name) {
    return new MapType(name, null);
  }

  public static MapType withNameAndDescription(String name, String description) {
    return new MapType(name, description);
  }

}
