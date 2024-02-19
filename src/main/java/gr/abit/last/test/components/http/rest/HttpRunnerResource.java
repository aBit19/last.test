package gr.abit.last.test.components.http.rest;

import gr.abit.last.test.components.http.runner.HttpRequestRunner;
import gr.abit.last.test.contract.Assertion.TestResult;
import gr.abit.last.test.components.http.runner.HttpTest;
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

  private final HttpRequestRunner requestRunner;

  @Inject
  public HttpRunnerResource(HttpRequestRunner requestRunner) {
    this.requestRunner = requestRunner;
  }

  @POST
  @Path("http")
  @Consumes("application/json")
  public Response runTest(HttpTest requestStep) {
    List<TestResult> results = requestRunner.run(requestStep);

    return Response.ok(results).build();
  }

  @GET
  public Response ping() {
    Log.info("Receive Ping");
    return Response.ok("Pong").build();
  }

}
