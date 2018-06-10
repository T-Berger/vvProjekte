package vv.restwebservice.services.interfacesService;

import org.jvnet.hk2.spring.bridge.api.SpringScope;
import vv.restwebservice.modells.Customer;

import java.util.List;

public interface ICustomerService {
//        List<Customer> getAllCustomers();
        Customer getCustomerById(long id);
        boolean addCustomer(Customer customer);
        void updateCustomer(Customer customer);
        void deleteCustomer(long id);
}
