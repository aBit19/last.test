package gr.abit.last.test.store;

import gr.abit.last.test.rest.Deserializer;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

@Path("/api/store/")
public class StoreResource {

  private final StoreService storeService;
  private final Deserializer deserializer;

  @Inject
  public StoreResource(StoreService storeService, Deserializer deserializer) {
    this.storeService = storeService;
    this.deserializer = deserializer;
  }

  @POST
  @Path("{project}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response store(@PathParam("project") String project, String test) {
    return Response.created(
            URI.create(String.format("api/store/%s/%s", project, storeService.store(project, test))))
        .build();
  }

  @GET
  @Path("{project}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response get(@PathParam("project") String project) {
    Map<String, String> ids = storeService.get(project);
    List<TestDetails> result = new ArrayList<>();
    for (Entry<String, String> idToPayload: ids.entrySet()) {
      String id = idToPayload.getKey();
      String payload = idToPayload.getValue();
      TestDetails testDetails = deserializer.get(payload, TestDetails.class);
      testDetails.setId(id);
      testDetails.setUrl(URI.create(String.format("/api/store/%s/%s", project, id)));
      result.add(testDetails);
    }
    return Response.ok(result).build();
  }

  @GET
  @Path("{project}/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getById(
      @PathParam("project") String project,
      @PathParam("id") String id) {

    Optional<String> test = storeService.get(project, id);
    if (test.isPresent()) {
      return Response.ok(test.get()).build();
    } else {
      return Response.status(Status.NOT_FOUND).build();
    }
  }


}
