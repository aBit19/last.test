package gr.abit.anbtest.http.runner;

import gr.abit.anbtest.contract.testtype.EnumType;
import gr.abit.anbtest.contract.testtype.IntType;
import gr.abit.anbtest.contract.testtype.ListType;
import gr.abit.anbtest.contract.testtype.MapType;
import gr.abit.anbtest.contract.testtype.ObjectType;
import gr.abit.anbtest.contract.testtype.ObjectType.ObjectTypeBuilder;
import gr.abit.anbtest.contract.testtype.StringType;
import gr.abit.anbtest.contract.testtype.TestSchema;
import gr.abit.anbtest.contract.testtype.TestType;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HttpSchema implements TestSchema {

  @Override
  public String getName() {
    return "http-request";
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
                        HttpResponseAssertion.getTestType(),
                        HttpStatusCodeAssertion.getTestType()
                    ).withDescription("Http assertions").build()
                ).build()
        )
        .build();
  }
}
