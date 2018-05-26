package endpoints;

import modells.Contract;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import services.interfacesService.IContractService;


import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Stateless
public class ContractEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(ContractEndpoint.class);
    @Autowired
    private IContractService contractService;
    @GET
    @Path("/details")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContractDetails() {
        List<Contract> list = contractService.getAllContracts();
        return Response.ok(list).build();
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContractById(@PathParam("id") Integer id) {
        Contract contract = contractService.getContractById(id);
        return Response.ok(contract).build();
    }
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addContract(Contract contract) {
        boolean isAdded = contractService.addContract(contract);
        if (!isAdded) {
            logger.info("Contract already exits.");
            return Response.status(javax.ws.rs.core.Response.Status.CONFLICT).build();
        }
        return Response.created(URI.create("/spring-app/contract/"+ contract.getContractId())).build();
    }
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateContract(Contract contract) {
        contractService.updateContract(contract);
        return Response.ok(contract).build();
    }
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteContract(@PathParam("id") Integer id) {
        contractService.deleteContract(id);
        return Response.noContent().build();
    }
}
