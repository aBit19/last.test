package gr.abit.last.test.plugins.http.rest;

import gr.abit.last.test.plugins.http.runner.HttpBasicAuth;
import gr.abit.last.test.rest.schema.EnumType;
import gr.abit.last.test.rest.schema.IntType;
import gr.abit.last.test.rest.schema.ListType;
import gr.abit.last.test.rest.schema.MapType;
import gr.abit.last.test.rest.schema.ObjectType;
import gr.abit.last.test.rest.schema.ObjectType.ObjectTypeBuilder;
import gr.abit.last.test.rest.schema.StringType;
import gr.abit.last.test.rest.schema.TestSchema;
import gr.abit.last.test.rest.schema.TestType;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HttpSchema implements TestSchema {

  @Override
  public String getType() {
    return "http";
  }

  @Override
  public String getDescription() {
    return "Run http requests.";
  }

  @Override
  public TestType getDefinition() {
    ObjectTypeBuilder builder = ObjectType.getBuilder();
    return builder.withFields(
            StringType.withName("id"),
            EnumType.getBuilder()
                .withFieldName("method")
                .withEnums(StringType.withName("GET"), StringType.withName("POST"))
                .withDescription("The http method").build(),
            StringType.withNameAndDescription("body", "The body to be sent."),
            IntType.withName("timeout"),
            MapType.withName("headers"),
            StringType.withNameAndDescription("destination", "The url of the request."),
            ObjectType.getBuilder().withName("authorization")
                .withFields(HttpBasicAuth.getTestSchema()).build(),
            ListType.getBuilder()
                .withFieldName("assertions")
                .withDescription("Test assertions")
                .ofType(
                    EnumType.getBuilder().withEnums(
                        responseAssertion(),
                        statusCodeAssertion()
                    ).withDescription("Http assertions").build()
                ).build()
        )
        .build();
  }

  private static TestType responseAssertion() {
    return ObjectType.getBuilder().withFields(
        StringType.withNameAndDescription("expectedBody", "The expected body")).build();
  }

  static TestType statusCodeAssertion() {
    return ObjectType.getBuilder().withFields(
        IntType.withNameAndDescription("expectedStatusCode", "The expected status code")).build();
  }

}
