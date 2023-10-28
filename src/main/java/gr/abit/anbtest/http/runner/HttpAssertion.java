package gr.abit.anbtest.http.runner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import gr.abit.anbtest.contract.Assertion;
import java.net.http.HttpResponse;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
    @JsonSubTypes.Type(HttpResponseAssertion.class),
    @JsonSubTypes.Type(HttpStatusCodeAssertion.class) }
)
public interface HttpAssertion extends Assertion<HttpResponse<String>> {

}
