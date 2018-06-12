package vv.restwebservice.proxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import vv.restwebservice.modells.Contract;
import vv.restwebservice.modells.Customer;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/** Diese Klasse ist nur eine Spielerei
 *  --
 *  Funktion:
 *  Wandelt ein Java Klasse von Typ Contract in eine Json Notation für die URL um.
 *  Also MAPPT ein POJO (Plain Old Java Object) zu einen Json String, diser dann
 *  in eine URL Adresse integriert wird
 *  --
 * **/
public class JsonURI {
    private int PORT = 8080;
    private final URI base_URi_Server = URI.create("http://localhost:8080/");
    private final String base_URi_Customer = ("/spring-app/customer");
    private final String base_URi_Contract = ("/spring-app/contract");

    Client client;
    JsonURI(){
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("user", "qwer");
        Client client = ClientBuilder.newClient().register(feature);
    }
    public void convert(Contract c) throws IOException {
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("user", "qwer");
        Client client = ClientBuilder.newClient().register(feature);

        System.out.println(c.toString());
        ObjectMapper mapper = new ObjectMapper();

        //POJO to JSON
        mapper.writeValue(new File("article.json"), c);
        String s = mapper.writeValueAsString(c);
        System.out.println(s);
        System.out.println("json created!");
        //        URI uriContract = UriBuilder.fromUri(base_URi_Server).uri(base_URi_Contract).uri.port(PORT).build();
        WebTarget webTarget = client.target("http://localhost:8080/").path("/spring-app/contract/full/{contract}").resolveTemplate("contract",s);
        System.out.println(webTarget);
        Response response = webTarget.request(MediaType.APPLICATION_JSON)
                .delete();
        System.out.println(response);
    }
    public static void main(String[] args) throws ParseException, IOException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");

        Customer margred = new Customer("Harry", "Hirsch", df.parse("1998.05.25"),"");
        new JsonURI().convert(new Contract("AA","BB",2,margred));
    }
}
