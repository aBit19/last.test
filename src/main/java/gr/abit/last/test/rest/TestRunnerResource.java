package gr.abit.last.test.rest;

import gr.abit.last.test.contract.Assertion.TestResult;
import io.quarkus.arc.All;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.util.List;
import java.util.Optional;

@Path("/api/run/")
public class TestRunnerResource {

  @Inject
  @All
  List<TestRunnerRest> runners;

  @POST
  @Path("{testCode}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response runTest(
      @PathParam("testCode") String testCode,
      String scriptDetails) {
    Optional<TestRunnerRest> first = runners.stream().filter(r -> r.getTestCode().equals(testCode))
        .findFirst();
    if (first.isEmpty()) {
      return Response.status(
              Status.NOT_IMPLEMENTED.getStatusCode(),
              "Test type not implemented")
          .build();
    }

    TestRunnerRest testRunnerRest = first.get();
    List<TestResult> assertions = testRunnerRest.runTest(scriptDetails);
    return Response.ok(assertions).build();
  }

  @GET
  public Response ping() {
    Log.info("Receive Ping");
    return Response.ok("Pong").build();
  }

}
