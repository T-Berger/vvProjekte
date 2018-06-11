package vv.restwebservice.proxy;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

class BasicProxyFunctions {
    private Client client;
    //    private final String authToken ="$2a$04$x9sT8qPPOeS7bsWHHAUEY.9qtschwL6qsZ/53TYaezM4KtzSfQmpy";
    BasicProxyFunctions(){
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("user", "qwer");
        client = ClientBuilder.newClient().register(feature);

    }


    public <T> void update(URI uri, T toUpdate) {
        //        Client client = ClientBuilder.newClient();
        WebTarget base = client.target(uri);
        WebTarget update = base.path("update");
        Response response = update.request(MediaType.APPLICATION_JSON)
                .put(Entity.json(toUpdate));//.header("Authorization", "Basic " + authToken)
        System.out.println("Response Http Status: "+ response.getStatus());
        //        T resContract = (T)response.readEntity(toUpdate.getClass());
        System.out.println(response);
    }

    public <T> void add (URI uri, T toAdd){
        //        Client client = ClientBuilder.newClient();
        WebTarget base = client.target(uri);
        WebTarget add = base.path("add");
        System.out.println(toAdd.toString());
        Response response = add.request(MediaType.APPLICATION_JSON)
                .post(Entity.json(toAdd));
        //.header("Authorization", "Basic " + authToken)
        System.out.println("Response Http Status: "+ response.getStatus());
        System.out.println(response.getLocation());
        //        client.close();
    }
    public void delete (URI uri, int toDelete){
        //        Client client = ClientBuilder.newClient();
        WebTarget base = client.target(uri);
        WebTarget deleteById = base.path("{id}").resolveTemplate("id", toDelete);
        Response response = deleteById.request(MediaType.APPLICATION_JSON)
                .delete();
        //.header("Authorization", "Basic " + authToken)
        System.out.println("Response Http Status: "+ response.getStatus());
        if(response.getStatus() == 204) {
            System.out.println("Data deleted successfully.");
        }
        //        client.close();
    }

    public void close(){
        client.close();
    }
}
