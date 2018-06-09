package vv.restwebservice.dao.interfacesDAO;


import vv.restwebservice.modells.Customer;

import java.util.List;

public interface ICustomerDAO{
    List<Customer> getAllCustomers();
    Customer getCustomerById(int customerId);
//    boolean addCustomer(Customer customer);
//    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(int customerId);
}
