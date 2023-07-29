package gr.abit.anbtest.http.rest;

import gr.abit.anbtest.contract.VerifierResult;
import gr.abit.anbtest.http.runner.HttpRequestRunner;
import gr.abit.anbtest.http.runner.HttpTest;
import java.util.List;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import io.quarkus.logging.Log;

@Path("/api/run/")
public class HttpRunnerResource {

  private final HttpRequestRunner runnerCoordinator;

  @Inject
  public HttpRunnerResource(HttpRequestRunner runnerCoordinator) {
    this.runnerCoordinator = runnerCoordinator;
  }

  @POST
  @Path("http")
  @Consumes("application/json")
  public Response runTest(HttpTest requestStep) {
    List<VerifierResult> results = runnerCoordinator.run(requestStep);
    return Response.ok(results).build();
  }

  @GET
  public Response ping() {
    Log.info("Receive Ping");
    return Response.ok("Pong").build();
  }

}
