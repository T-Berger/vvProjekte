package vv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(scanBasePackages={
        "vv.restwebservice"})
@ComponentScan("vv")
@ComponentScan("vv.restwebservice.services.interfacesService")
@ComponentScan({"vv.restwebservice.endpoints"})
@EntityScan("com.delivery.domain")
public class RestWebserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestWebserviceApplication.class, args);
    }
}