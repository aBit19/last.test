package gr.abit.anbtest.http.runner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import gr.abit.anbtest.contract.Verifier;
import java.net.http.HttpResponse;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
    @JsonSubTypes.Type(HttpResponseVerifier.class),
    @JsonSubTypes.Type(HttpStatusCodeVerifier.class) }
)
public interface HttpVerifier extends Verifier<HttpResponse<String>> {

}
