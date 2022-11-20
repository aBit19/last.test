package gr.abit.anbtest.runner.impl.http.verifiers;

import gr.abit.anbtest.runner.contract.VerifierType;
import java.net.http.HttpResponse;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface HttpVerifierType<T extends HttpVerifier> extends
    VerifierType<Map<String, Map<String, String>>, HttpResponse<String>, T> {
}
