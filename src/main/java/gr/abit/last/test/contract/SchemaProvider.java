package gr.abit.last.test.contract;

import io.quarkus.arc.All;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;

@ApplicationScoped
public class SchemaProvider {
  @Inject
  @All
  List<TestSchema> schemas;

  public Collection<TestSchema> getSchemas() {
    return schemas;
  }

}
