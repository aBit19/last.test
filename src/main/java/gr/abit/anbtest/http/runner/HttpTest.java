package gr.abit.anbtest.http.runner;

import gr.abit.anbtest.contract.Test;
import gr.abit.anbtest.contract.testtype.EnumType;
import gr.abit.anbtest.contract.testtype.IntType;
import gr.abit.anbtest.contract.testtype.ListType;
import gr.abit.anbtest.contract.testtype.MapType;
import gr.abit.anbtest.contract.testtype.ObjectType;
import gr.abit.anbtest.contract.testtype.ObjectType.ObjectTypeBuilder;
import gr.abit.anbtest.contract.testtype.StringType;
import gr.abit.anbtest.contract.testtype.TestSchema;
import gr.abit.anbtest.contract.testtype.TestType;
import java.util.List;
import java.util.Map;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HttpTest implements Test {

  String id;
  String method;
  String body;
  long timeout;
  Map<String, String> headers;
  String destination;
  HttpBasicAuth authorization;
  List<HttpAssertion> assertions;

  @Override
  public TestSchema getSchema() {
    return new HttpSchema();
  }

  @ApplicationScoped
  public static class HttpSchema implements TestSchema {

    @Override
    public String getName() {
      return "http-request";
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
}
