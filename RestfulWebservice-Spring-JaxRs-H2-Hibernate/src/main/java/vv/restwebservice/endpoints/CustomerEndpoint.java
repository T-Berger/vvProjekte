package vv.restwebservice.endpoints;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vv.restwebservice.modells.Customer;
import vv.restwebservice.services.interfacesService.ICustomerService;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Component
@Path("/customer")
@Stateless
@Api
public class CustomerEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(CustomerEndpoint.class);
    @Autowired
    private ICustomerService customerService;
//    @GET
//    @Path("/details")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getCustomerDetails() {
//        List<Customer> list = customerService.getAllCustomers();
//        return Response.ok(list).build();
//    }

//    @ApiOperation("Updates an existing student.")
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("id") Integer id) {
        Customer customer = customerService.getCustomerById(id);
        return Response.ok(customer).build();
    }
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCustomer(Customer customer) {
        boolean isAdded = customerService.addCustomer(customer);
        if (!isAdded) {
            logger.info("Customer already exits.");
            return Response.status(Response.Status.CONFLICT).build();
        }

        return Response.created(URI.create("/spring-app/customer/"+ customer.getid())).build();
    }
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCustomer(Customer customer) {
        customerService.updateCustomer(customer);
        return Response.ok(customer).build();
    }
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteCustomer(@PathParam("id") Integer id) {

        customerService.deleteCustomer(id);
        return Response.noContent().build();
    }
}
