package vv.restwebservice.services.interfacesService;

import vv.restwebservice.modells.Customer;

import java.util.List;

public interface ICustomerService {
        List<Customer> getAllCustomers();
        Customer getCustomerById(long id);
        boolean addCustomer(Customer customer);
        void updateCustomer(Customer customer);
        void deleteCustomer(long id);
        boolean existByID (long id);
}
