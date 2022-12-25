package gr.abit.anbtest;

import gr.abit.anbtest.contract.testtype.TestSchema;
import io.quarkus.arc.All;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

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
