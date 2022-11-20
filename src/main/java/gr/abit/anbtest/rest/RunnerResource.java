package gr.abit.anbtest.rest;

import gr.abit.anbtest.runner.contract.RunnerCoordinator;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import io.quarkus.logging.Log;

@Path("/api/run/")
public class RunnerResource {

  private final RunnerCoordinator runnerCoordinator;

  @Inject
  public RunnerResource(RunnerCoordinator runnerCoordinator) {
    this.runnerCoordinator = runnerCoordinator;
  }

  @POST
  @Path("{type}")
  @Consumes("application/json")
  public Response runTest(
      @PathParam("type") String type,
      String testDetails) {
    Object coordinate = runnerCoordinator.handleTest(type, testDetails);
    return Response.ok(coordinate).build();
  }

  @GET
  public Response ping() {
    Log.info("Receive Ping");
    return Response.ok("Pong").build();
  }

}
