package gr.abit.anbtest.runner.impl.http;

import gr.abit.anbtest.runner.contract.Verifier;
import java.net.http.HttpResponse;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public abstract class HttpVerifier implements Verifier<HttpResponse<String>> {

}
