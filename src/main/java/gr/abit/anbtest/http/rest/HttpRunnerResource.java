package gr.abit.anbtest.http.rest;

import gr.abit.anbtest.contract.VerifierResult;
import gr.abit.anbtest.http.runner.HttpRequestRunner;
import gr.abit.anbtest.http.runner.HttpRequestStep;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

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
  public Response runTest(HttpRequestStep requestStep) {
    List<VerifierResult> results = runnerCoordinator.run(requestStep);
    return Response.ok(results).build();
  }

  @GET
  public Response ping() {
    Log.info("Receive Ping");
    return Response.ok("Pong").build();
  }

}
