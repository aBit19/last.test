package gr.abit.anbtest.store;


import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import java.net.URI;

@Path("/api/store/")
public class StoreResource {

  private final StoreService storeService;

  @Inject
  public StoreResource(StoreService storeService) {
    this.storeService = storeService;
  }

  @POST
  @Path("{type}")
  @Consumes("application/json")
  public Response store(
      @PathParam("type") String type, String testDetails) {
    return Response.created(URI.create(String.format("api/store/%s/%s", type, storeService.store(type, testDetails)))).build();
  }

  @GET
  @Path("{type}/{id}")
  @Produces("application/json")
  public Response get(
      @PathParam("type") String type, @PathParam("id") String id) {
    return Response.ok(storeService.get(type, id)).build();
  }

}
