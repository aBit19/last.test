package gr.abit.last.test.rest;

import io.quarkus.arc.All;
import gr.abit.last.test.rest.schema.TestSchema;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.net.URI;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@ApplicationScoped
@Path("/api/schemas/")
public class TestSchemaResource {

  @Inject
  @All
  List<TestSchema> schemas;

  @GET
  public Response get() {
    return Response.ok(
        schemas.stream()
            .map(sc -> new TestSchemaSummary(sc.getTestCode(), sc.getDescription(),
                URI.create("/api/schemas/" + sc.getTestCode())))
            .toList()).build();
  }

  @GET
  @Path("{name}")
  public Response getSchema(@PathParam("name") String name) {
    var findByName = schemas.stream().filter(sc -> sc.getTestCode().equals(name)).findAny();
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
