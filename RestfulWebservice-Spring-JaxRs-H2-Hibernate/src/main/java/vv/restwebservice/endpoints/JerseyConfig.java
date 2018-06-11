package vv.restwebservice.endpoints;

import io.swagger.annotations.Api;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import vv.restwebservice.endpoints.ContractEndpoint;
//import vv.restwebservice.endpoints.CustomerEndpoint;

import javax.ws.rs.ApplicationPath;
import java.util.ResourceBundle;

@Component
@ApplicationPath("/spring-app")

public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(ContractEndpoint.class);
        register(CustomerEndpoint.class);
    }
}
