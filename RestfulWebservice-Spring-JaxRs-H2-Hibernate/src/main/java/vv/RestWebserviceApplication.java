package vv;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
//import vv.restwebservice.dao.ContractDAO;
//import vv.restwebservice.dao.CustomerDAO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import vv.restwebservice.dao.interfacesDAO.ICustomerDAO;
import vv.restwebservice.modells.Address;
import vv.restwebservice.modells.Contract;
import vv.restwebservice.modells.Customer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@SpringBootApplication
public class RestWebserviceApplication {
    @Bean
    InitializingBean seedDatabase(ICustomerDAO repo) throws ParseException {
        return new InitializingBean() {
            @Override
            public void afterPropertiesSet() throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");

        Customer margred = new Customer("Harry", "Hirsch", df.parse("1998.05.25"),"");
        Address address = new Address("foo","1","bar");
        List<Contract> contracts = new ArrayList<>();
        contracts.add(new Contract("Auto","KFH A",85264,margred));
        contracts.add(new Contract("Auto","KFH A",85264,margred));
        margred.setContracts(contracts);
        margred.setAddress(address);
        repo.save(margred);
            }
        };
    }
    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(createStandardConnector());
        return tomcat;
    }

    private Connector createStandardConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setPort(8080);
        return connector;
    }


    public static void main(String[] args) {
        SpringApplication.run(RestWebserviceApplication.class, args);
    }

//    @Bean
//    BasicAuthenticationFilter basicAuthFilter(AuthenticationManager authenticationManager, BasicAuthenticationEntryPoint basicAuthEntryPoint) {
//        return new BasicAuthenticationFilter(authenticationManager, basicAuthEntryPoint());
//    }
//    @Bean
//    BasicAuthenticationEntryPoint basicAuthEntryPoint() {
//        BasicAuthenticationEntryPoint bauth = new BasicAuthenticationEntryPoint();
//        bauth.setRealmName("GAURAVBYTES");
//        return bauth;
//    }
}