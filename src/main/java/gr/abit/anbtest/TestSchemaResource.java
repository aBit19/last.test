package gr.abit.anbtest;

import gr.abit.anbtest.contract.testtype.TestSchema;
import io.quarkus.arc.All;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@ApplicationScoped
@Path("/api/tests/schemas/")
public class TestSchemaResource {

  @Inject
  @All
  List<TestSchema> schemas;

  @GET
  public List<TestSchema> get() {
    return schemas;
  }


}
