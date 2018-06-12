package vv.restwebservice.endpoints;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/spring-app")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(ContractEndpoint.class);
        register(CustomerEndpoint.class);
    }
}
