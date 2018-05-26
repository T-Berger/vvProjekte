package Controller;

import modells.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import services.interfacesService.IContractService;

import java.util.List;

@Controller
@RequestMapping("user")
public class ContractController {
    @Autowired
    private IContractService contractService;
    @GetMapping("contract/{id}")
    public ResponseEntity<Contract> getContractById(@PathVariable("id") Integer id) {
        Contract contract = contractService.getContractById(id);
        return new ResponseEntity<Contract>(contract, HttpStatus.OK);
    }
    @GetMapping("contracts")
    public ResponseEntity<List<Contract>> getAllContracts() {
        List<Contract> list = contractService.getAllContracts();
        return new ResponseEntity<List<Contract>>(list, HttpStatus.OK);
    }
    @PostMapping("contract")
    public ResponseEntity<Void> addContract(@RequestBody Contract contract, UriComponentsBuilder builder) {
        boolean flag = contractService.addContract(contract);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/contract/{id}").buildAndExpand(contract.getContractId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    @PutMapping("contract")
    public ResponseEntity<Contract> updateContract(@RequestBody Contract contract) {
        contractService.updateContract(contract);
        return new ResponseEntity<Contract>(contract, HttpStatus.OK);
    }
    @DeleteMapping("contract/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable("id") Integer id) {
        contractService.deleteContract(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
