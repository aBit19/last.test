package gr.abit.anbtest.contract.store;


import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.net.URI;
import java.util.Optional;

@Path("/api/store/")
public class StoreResource {

  private final StoreService storeService;

  @Inject
  public StoreResource(StoreService storeService) {
    this.storeService = storeService;
  }

  @POST
  @Consumes("application/json")
  public Response store(String testDetails) {
    return Response.created(
            URI.create(String.format("api/store/%s", storeService.store(testDetails))))
        .build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response get() {
    return Response.ok().build();
  }

  @GET
  @Path("{id}")
  @Produces("application/json")
  public Response getById(@PathParam("id") String id) {

    Optional<String> test = storeService.get(id);
    if (test.isPresent()) {
      return Response.ok(test.get()).build();
    } else {
      return Response.status(Status.NOT_FOUND).build();
    }
  }

}
