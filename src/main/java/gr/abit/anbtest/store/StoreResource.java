package gr.abit.anbtest.store;


import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
