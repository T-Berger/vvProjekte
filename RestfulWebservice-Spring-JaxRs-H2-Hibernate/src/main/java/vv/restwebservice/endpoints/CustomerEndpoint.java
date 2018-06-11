package vv.restwebservice.endpoints;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vv.restwebservice.modells.Customer;
import vv.restwebservice.services.ContractService;
import vv.restwebservice.services.CustomerService;
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
    @GET
    @Path("/customers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerDetails() {
        List<Customer> list = customerService.getAllCustomers();
        return Response.ok(list).build();
    }

    @ApiOperation("Updates an existing student.")
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("id") Integer id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null){
            logger.info("CustomerID doesn't exits.");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(customer).build();
    }
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCustomer(Customer customer) {
        boolean isAdded = customerService.addCustomer(customer);
        if (!isAdded) {
            logger.info("Customer already existing.");
            return Response.status(Response.Status.CONFLICT).build();
        }

        return Response.created(URI.create("/spring-app/customer/"+ customer.getid())).build();
    }
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCustomer(Customer customer) {
        if(customer.getid() == 0 ){
            logger.info("WARNING Customer is not existing, creating new Customer\".");
            return addCustomer(customer);
        }
        if( customerService.existByID(customer.getid())){
            logger.info("WARNING Customer is not existing, creating new Customer\".");
            return addCustomer(customer);
        }
        System.out.println(customer.toString());
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
