package gr.abit.last.test.rest;

import gr.abit.last.test.contract.SchemaProvider;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.net.URI;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@ApplicationScoped
@Path("/api/schemas/")
public class TestSchemaResource {

  private final SchemaProvider schemaProvider;

  @Inject
  public TestSchemaResource(SchemaProvider schemaProvider) {
    this.schemaProvider = schemaProvider;
  }

  @GET
  public Response get() {
    return Response.ok(
        schemaProvider.getSchemas().stream()
            .map(sc -> new TestSchemaSummary(sc.getName(), sc.getDescription(),
                URI.create("/api/schemas/" + sc.getName())))
            .toList()).build();
  }

  @GET
  @Path("{name}")
  public Response getSchema(@PathParam("name") String name) {
    var findByName = schemaProvider.getSchemas().stream().filter(sc -> sc.getName().equals(name)).findAny();
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
