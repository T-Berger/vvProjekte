package dao.interfacesDAO;

import modells.Customer;
import modells.Customer;

import java.util.List;

public interface ICustomerDAO{
    List<Customer> getAllCustomers();
    Customer getCustomerById(int customerId);
//    boolean addCustomer(Customer customer);
    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(int customerId);
}
