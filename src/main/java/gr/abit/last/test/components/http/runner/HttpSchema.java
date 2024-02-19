package gr.abit.last.test.components.http.runner;

import gr.abit.last.test.contract.testtype.EnumType;
import gr.abit.last.test.contract.testtype.IntType;
import gr.abit.last.test.contract.testtype.ListType;
import gr.abit.last.test.contract.testtype.MapType;
import gr.abit.last.test.contract.testtype.ObjectType;
import gr.abit.last.test.contract.testtype.ObjectType.ObjectTypeBuilder;
import gr.abit.last.test.contract.testtype.StringType;
import gr.abit.last.test.contract.TestSchema;
import gr.abit.last.test.contract.testtype.TestType;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HttpSchema implements TestSchema {

  @Override
  public String getName() {
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
                        HttpResponseAssertion.getTestType(),
                        HttpStatusCodeAssertion.getTestType()
                    ).withDescription("Http assertions").build()
                ).build()
        )
        .build();
  }
}
