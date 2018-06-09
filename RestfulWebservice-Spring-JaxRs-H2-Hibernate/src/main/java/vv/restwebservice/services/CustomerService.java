package vv.restwebservice.services;


import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import vv.restwebservice.dao.interfacesDAO.ICustomerDAO;
import vv.restwebservice.modells.Customer;
import vv.restwebservice.services.interfacesService.ICustomerService;

import java.util.List;

@org.springframework.stereotype.Service
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
