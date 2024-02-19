package gr.abit.last.test.components.http.rest;

import gr.abit.last.test.components.http.runner.HttpRequestRunner;
import gr.abit.last.test.components.http.runner.HttpSchema;
import gr.abit.last.test.contract.Assertion.TestResult;
import gr.abit.last.test.components.http.runner.HttpTest;
import gr.abit.last.test.contract.testtype.TestType;
import gr.abit.last.test.rest.TestSchemaRest;
import jakarta.enterprise.context.ApplicationScoped;
import java.net.URI;
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

  @ApplicationScoped
  public static class HttpSchemaRest implements TestSchemaRest {

    @Inject
    private HttpSchema httpSchema;

    @Override
    public String getName() {
      return httpSchema.getName();
    }

    @Override
    public String getDescription() {
      return httpSchema.getDescription();
    }

    @Override
    public TestType getDefinition() {
      return httpSchema.getDefinition();
    }

    @Override
    public URI getRunEndpoint() {
      return URI.create("/api/run/http");
    }
  }
}
