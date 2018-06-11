package vv.restwebservice.Controller;


import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vv.restwebservice.modells.Customer;
import vv.restwebservice.services.CustomerService;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

//@Controller
@RequestMapping("customer")
@RestController
@Api(tags = "students")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @GetMapping("customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Integer id) {
        Customer customer = customerService.getCustomerById(id);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @GetMapping("customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> list = customerService.getAllCustomers();
        return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
    }
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @PostMapping("customer")
    public ResponseEntity<Void> addCustomer(@RequestBody Customer customer, UriComponentsBuilder builder) {
        boolean flag = customerService.addCustomer(customer);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/customer/{id}").buildAndExpand(customer.getid()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @PutMapping("customer")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        customerService.updateCustomer(customer);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @DeleteMapping("customer/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Integer id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


}
