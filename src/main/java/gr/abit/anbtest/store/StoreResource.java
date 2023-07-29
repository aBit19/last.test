package gr.abit.anbtest.store;


import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

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
  public Response runTest(
      @PathParam("type") String type, String testDetails) {
    storeService.store(type, testDetails);
    return Response.ok(Status.CREATED).build();
  }

}
