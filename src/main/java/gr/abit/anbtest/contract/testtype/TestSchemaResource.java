package gr.abit.anbtest.contract.testtype;

import gr.abit.anbtest.contract.testtype.TestSchema;
import io.quarkus.arc.All;
import io.smallrye.common.constraint.NotNull;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.net.URI;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApplicationScoped
@Path("/api/tests/schemas/")
public class TestSchemaResource {

  @Inject
  @All
  List<TestSchema> schemas;

  @GET
  public Response get() {
    return Response.ok(
        schemas.stream().map(sc -> new TestSchemaSummary(sc.getName(), sc.getDescription(),
                URI.create("/api/tests/schemas/" + sc.getName())))
            .toList()).build();
  }

  @GET
  @Path("{name}")
  public Response getSchema(@PathParam("name") String name) {
    var findByName = schemas.stream().filter(sc -> sc.getName().equals(name)).findAny();
    if (findByName.isPresent()) {
      return Response.ok(findByName).build();
    } else {
      return Response.status(Status.NOT_FOUND).build();
    }
  }

  @Getter
  @Setter
  @AllArgsConstructor
  public static class TestSchemaSummary {
    String name;
    String description;
    URI uri;
  }

}