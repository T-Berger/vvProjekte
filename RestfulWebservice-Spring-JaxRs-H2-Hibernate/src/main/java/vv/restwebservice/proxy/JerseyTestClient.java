package vv.restwebservice.proxy;
import org.hibernate.annotations.AnyMetaDef;
import org.springframework.data.domain.AfterDomainEventPublication;
import vv.restwebservice.modells.Address;
import vv.restwebservice.modells.Contract;
import vv.restwebservice.modells.Customer;

import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.PrintStream;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
@Stateless
public class JerseyTestClient {
    private int PORT = 8080;
    private final URI base_URi_Server = URI.create("http://localhost:8080/");
    private final String base_URi_Customer = ("/spring-app/customer");
    private final String base_URi_Contract = ("/spring-app/contract");


    private final URI uriCustomer;
    private final URI uriContract;
    private final String authToken ="$2a$04$x9sT8qPPOeS7bsWHHAUEY.9qtschwL6qsZ/53TYaezM4KtzSfQmpy";
    public BasicProxyFunctions basicProxyFunctions;
    public JerseyTestClient() {
        uriContract = UriBuilder.fromUri(base_URi_Server).uri(base_URi_Contract).port(PORT).build();
        uriCustomer = UriBuilder.fromUri(base_URi_Server).path(base_URi_Customer).port(PORT).build();
        basicProxyFunctions = new BasicProxyFunctions();
    }

    public void getCustomerDetails() {
        Client client = ClientBuilder.newClient().register( new Authenticator("user","537ac6be-8881-4fd9-94a9-68607007f77c"));
//        WebTarget base = client.target("http://localhost:8080/spring-app/customer");
//        WebTarget details = base.path("details");
        WebTarget details = client.target(uriCustomer);
        List<Customer> list = details.request(MediaType.APPLICATION_JSON).header("Authorization", "Basic " + authToken)
                .get(new GenericType<List<Customer>>() {});
        list.stream().forEach(customer -> customer.toString());
//        list.stream().forEach(customer ->
//                System.out.println(
//                        customer.getid()+", "+ customer.getSurname()+", "+ customer.getForename()
//                        +", "+ customer.getAddress().toString()+", "+ customer.getDescription()+", " +
//                                ""+ customer.getBirthday()+", "));
//                        customer.getContracts().forEach(contract -> System.out.println(contract.toString()));

//        client.close();
    }
    public Customer getCustomerById(int customerId) {
        Client client = ClientBuilder.newClient();
        WebTarget base = client.target(uriCustomer);
        WebTarget customerById = base.path("{id}").resolveTemplate("id", customerId);
        System.out.println(uriCustomer);
        System.out.println(customerById);
        Customer customer = customerById.request(MediaType.APPLICATION_JSON).header("Authorization", "Basic " + authToken)
                .get(Customer.class);
        System.out.println(customer.toString());
        return customer;
//        client.close();
    }
    public void addCustomer(Customer customer) {
        basicProxyFunctions.add(uriCustomer,customer);
    }
    public void updateCustomer(Customer customer) {
        basicProxyFunctions.update(uriCustomer,  customer);
    }

    public void deleteCustomer(int customerId) {
        basicProxyFunctions.delete(uriCustomer,customerId);
    }

    public List<Contract> getContractDetails() {
        Client client = ClientBuilder.newClient();
//        WebTarget base = client.target(uriContract);
//        WebTarget details = base.path("details");
        WebTarget details = client.target(uriContract);
        List<Contract> list = details.request(MediaType.APPLICATION_JSON).header("Authorization", "Basic " + authToken)
                .get(new GenericType<List<Contract>>() {});

        list.stream().forEach(contract ->
                System.out.println(contract.getContractId()+", "+ contract.getCustomer().getid()+", "+ contract.getSurname()+
                        ", "+ contract.getPrice()+", "+ contract.getType()));

        client.close();
        return list;
    }

    public Contract getContractById(int contractId) {
        Client client = ClientBuilder.newClient();
        WebTarget base = client.target(uriContract);
        WebTarget contractById = base.path("{id}").resolveTemplate("id", contractId);
        Contract contract = contractById.request(MediaType.APPLICATION_JSON).header("Authorization", "Basic " + authToken)
                .get(Contract.class);
        System.out.println(contract.toString());
        System.out.println(contract.getContractId()+", "+ contract.getForeignKey()+", "+ contract.getSurname()+
                ", "+ contract.getPrice()+", "+ contract.getType());
//        client.close();
        return contract;
    }
    public void addContract(Contract contract) {
        basicProxyFunctions.add(uriContract,contract);
    }
    public void updateContract(Contract contract) {
        basicProxyFunctions.update(uriContract,contract);
    }
    public void deleteContract(int contractId) {
        basicProxyFunctions.delete(uriContract,contractId);
    }

    public static void main(String[] args) throws ParseException {
        JerseyTestClient jerseyClient = new JerseyTestClient();
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
        Customer margred = new Customer("MARGRED", "TEST", df.parse("1998.05.25"),"");
        Address address = new Address("ZWERGENSTADT","12212","TOONSWORLD");
        List<Contract> contracts = new ArrayList<>();
        contracts.add(new Contract("Auto","KFH A",85264,margred));
        contracts.add(new Contract("Brandschutz","KFH A",85264,margred));
        margred.setContracts(contracts);
        margred.setAddress(address);
        jerseyClient.addCustomer(margred);
//        jerseyClient.addCustomer(margred);
        Customer mordred = new Customer("Mordred", "Mordred", df.parse("2000.00.00"),"");
        Address address1 = new Address("foo","1","bar");
        List<Contract> contracts1 = new ArrayList<>();
        contracts1.add(new Contract("ASD","ZDF",85264,mordred));
        contracts1.add(new Contract("YMCA","QWERTZ",10000,mordred));
        mordred.setContracts(contracts1);
        mordred.setAddress(address1);

        jerseyClient.addCustomer(mordred);
        jerseyClient.addCustomer(margred);

        Contract testContract =new Contract("TEST","QWERTY",0,margred);
        jerseyClient.addContract(testContract);// do the setup

//        jerseyClient.updateContract(new Contract("123","123",2,margred,9));

//        Contract c= jerseyClient.getContractById(3);

//        System.out.println(jerseyClient.getCustomerById(1));
//        System.out.println(c);
//        jerseyClient.deleteContract(2);
//        jerseyClient.deleteCustomer(2);
    }
}


