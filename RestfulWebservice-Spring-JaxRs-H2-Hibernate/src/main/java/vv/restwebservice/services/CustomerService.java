package services;

import dao.interfacesDAO.ICustomerDAO;
import modells.Contract;
import modells.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import services.interfacesService.IContractService;
import services.interfacesService.ICustomerService;

import java.util.List;


public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerDAO customerDAO;
    @Override
    public Customer getCustomerById(int customerId) {
        Customer obj = customerDAO.getCustomerById(customerId);
        return obj;
    }
    @Override
    public List<Customer> getAllCustomers(){
        return customerDAO.getAllCustomers();
    }
    @Override
    public synchronized boolean addCustomer(Customer customer){
//        if (customerDAO.customerExists(customer.getTitle(), customer.getCategory())) {
//            return false;
//        } else {
//            customerDAO.addCustomer(customer);
//            return true;
//        }
        return true;
    }
    @Override
    public void updateCustomer(Customer customer) {
        customerDAO.updateCustomer(customer);
    }
    @Override
    public void deleteCustomer(int customerId) {
        customerDAO.deleteCustomer(customerId);
    }
}
