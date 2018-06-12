package vv;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestWebserviceApplication {
    /*
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
    */
    @Bean
    /** Starts a new 2nd webserver
     * here used for the http Connection **/
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(createStandardConnector());
        return tomcat;
    }
    /**Connector for the 2nd Server Connection.
     * here used for the http Connection **/
    private Connector createStandardConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setPort(8080);
        return connector;
    }


    public static void main(String[] args) {
        SpringApplication.run(RestWebserviceApplication.class, args);
    }
}