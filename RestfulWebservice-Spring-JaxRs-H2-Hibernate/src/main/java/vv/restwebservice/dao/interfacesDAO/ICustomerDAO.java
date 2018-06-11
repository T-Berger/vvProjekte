package vv.restwebservice.dao.interfacesDAO;


import org.springframework.data.repository.CrudRepository;
import vv.restwebservice.modells.Customer;

public interface ICustomerDAO
        extends CrudRepository<Customer,Long> {
//    List<Customer> getAllCustomers();
//    Customer getCustomerById(long id);
////    boolean addCustomer(Customer customer);
//    void addCustomer(Customer customer);
//    void updateCustomer(Customer customer);
//    void deleteCustomer(int id);
}
