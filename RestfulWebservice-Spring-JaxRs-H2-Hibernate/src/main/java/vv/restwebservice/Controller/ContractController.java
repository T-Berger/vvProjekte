package vv.restwebservice.Controller;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vv.restwebservice.modells.Contract;
import vv.restwebservice.services.interfacesService.IContractService;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Controller
@RequestMapping("contract")
@RestController
@Api
public class ContractController {
    @Autowired
    private IContractService contractService;
    @GetMapping("contract/{contractId}")
    public ResponseEntity<Contract> getContractById(@PathVariable("contractId") Integer contractId) {
        Contract contract = contractService.getContractById(contractId);
        return new ResponseEntity<Contract>(contract, HttpStatus.OK);
    }
    @GetMapping("contracts")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public ResponseEntity<List<Contract>> getAllContracts() {
        List<Contract> list = contractService.getAllContracts();
        return new ResponseEntity<List<Contract>>(list, HttpStatus.OK);
    }
    @PostMapping("contract")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public ResponseEntity<Void> addContract(@RequestBody Contract contract, UriComponentsBuilder builder) {
        boolean flag = contractService.addContract(contract);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/contract/{contractId}").buildAndExpand(contract.getContractId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Transactional
    @PutMapping("contract")
    public ResponseEntity<Contract> updateContract(@RequestBody Contract contract) {
        contractService.updateContract(contract);
        return new ResponseEntity<Contract>(contract, HttpStatus.OK);
    }

    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Transactional
    @DeleteMapping("contract/{contractId}")
    public ResponseEntity<Void> deleteContract(@PathVariable("contractId") Long contractId) {
        contractService.deleteContract(contractId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
