import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;
@Stateless
public class JerseyTestClient {


    private URI uri;
    private Client client;
    public JerseyTestClient() {
        uri = UriBuilder
                .fromUri("http://localhost:8080/LibraryServer_JAXRS/rs/book")
                .port(8080).build();
        client = ClientBuilder.newClient();
    }
//
//    public String addNewBook(Kunde kunde){
//        Response response = client.target(uri)
//                .request()
//                .post(Entity.entity(kunde,MediaType.APPLICATION_XML));
//        return response.getStatusInfo().getReasonPhrase();
//    }
//
//    public List<Kunde> getBooks(){
//        List<Kunde> books = client.target(uri)
//                .request()
//                .get(new GenericType<List<Kunde>>(){});
//        return books;
//    }

    public void close(){
        client.close();
    }
}


