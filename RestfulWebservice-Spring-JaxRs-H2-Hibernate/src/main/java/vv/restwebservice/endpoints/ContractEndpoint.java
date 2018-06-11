package vv.restwebservice.endpoints;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vv.restwebservice.modells.Contract;
import vv.restwebservice.services.ContractService;
import vv.restwebservice.services.interfacesService.IContractService;

import javax.ejb.Stateless;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.util.List;
@Component
@Path("/contract")
@Stateless
@Api
public class ContractEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(ContractEndpoint.class);
    @Autowired
    private IContractService contractService;
    @GET
    @Path("/contracts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContractDetails() {
        List<Contract> list = contractService.getAllContracts();
        return Response.ok(list).build();
    }
    @GET
    @Path("/{contractId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContractById(@PathParam("contractId") Integer contractId) {
        Contract contract = contractService.getContractById(contractId);
        if (contract == null){
            logger.info("ContractID doesn't exits.");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(contract).build();
    }
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addContract(Contract contract) {
        boolean isAdded = contractService.addContract(contract);
        if (!isAdded) {
            logger.info("Contract already existing.");
            return Response.status(javax.ws.rs.core.Response.Status.CONFLICT).build();
        }
        return Response.created(URI.create("/spring-app/contract/"+ contract.getContractId())).build();
    }
    @PUT
    @Path("/update")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateContract(Contract contract) {
        if(contract.getContractId() == 0){
            logger.info("WARNING Contract is not existing, creating new Contract");
            return addContract(contract);
        }
        if( ! contractService.existByID(contract.getContractId())){
            logger.info("WARNING Contract is not existing, creating new Contract");
            return addContract(contract);
        }
        System.out.println(contract.toString());
        contractService.updateContract(contract);
        return Response.ok(contract).build();
    }
    @DELETE
    @Path("/{contractId}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteContract(@PathParam("contractId") Long contractId) {
        contractService.deleteContract(contractId);
        return Response.noContent().build();
    }
    @DELETE
    @Path("/full/{contract}")
    @Transactional
//    @Consumes(MediaType.APPLICATION_JSON)
    public  Response deleteContractByValues(@PathParam("contract") String c) throws IOException {
        Contract help = new ObjectMapper().readValue(c, Contract.class);
        System.out.println(help.toString());
        return Response.noContent().build();
    }

}
