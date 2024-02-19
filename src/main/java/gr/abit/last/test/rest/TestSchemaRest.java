package gr.abit.last.test.rest;

import gr.abit.last.test.contract.TestSchema;
import java.net.URI;

public interface TestSchemaRest extends TestSchema {
  URI getRunEndpoint();
}
