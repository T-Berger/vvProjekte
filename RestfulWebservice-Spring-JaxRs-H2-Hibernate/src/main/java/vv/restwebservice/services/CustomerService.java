package vv.restwebservice.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import vv.restwebservice.dao.interfacesDAO.ICustomerDAO;
import vv.restwebservice.modells.Customer;
import vv.restwebservice.services.interfacesService.ICustomerService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@org.springframework.stereotype.Service
@Transactional
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerDAO customerDAO;
    @Override
    public Customer getCustomerById(long id) {
//        Customer obj = customerDAO.getCustomerById(id);
        if (customerDAO.existsById(id)){
            Customer obj = customerDAO.findById(id).get();
            return obj;
        }
        return null;
    }
    @Override
    public List<Customer> getAllCustomers(){
        List<Customer> customers = new ArrayList<>();
        customerDAO.findAll().forEach(customers::add);
        return  customers;
    }
    @Override
    public synchronized boolean addCustomer(Customer customer){
        /////DAS HIER
        if (customerExists(customer)) {
            return false;
        } else {
            Customer c = customer;
            customerDAO.save(customer);
            // MAP contracts to Customer
            System.out.println(c);
            c.getContracts().forEach(contract -> {
                contract.setCustomer(c);
                contract.setForeignKey(c.getid());
            });
            customerDAO.save(c);
            return true;
        }
    }
    @Override
    /**
     * Update all fields form the saved Object with the new fields of the new Object**/
    public void updateCustomer(Customer customer) {
        Customer updatedCustomer = getCustomerById(customer.getid());
        updatedCustomer.setAddress(customer.getAddress());
        updatedCustomer.setContracts(customer.getContracts());
        // MAP contracts to Customer
        updatedCustomer.getContracts().forEach(contract -> {
            contract.setCustomer(customer);
            contract.setForeignKey(customer.getid());
        });
        updatedCustomer.setBirthday(customer.getBirthday());
        updatedCustomer.setDescription(customer.getDescription());
        updatedCustomer.setForename(customer.getDescription());
        updatedCustomer.setSurname(customer.getSurname());
        updatedCustomer.setid(customer.getid());
        customerDAO.save(customer);
    }
    @Override
    public void deleteCustomer(long id) {
        customerDAO.delete(customerDAO.findById(id).get());
    }
    /**
     * This method looks up if any other database entry has the same
     * not generated field/variable value as the @param Object
     * @return Return false if the Object doesn't exist in the h2 database  **/
    public boolean customerExists(Customer customer){
        AtomicBoolean returnFlag = new AtomicBoolean(false);
        getAllCustomers().forEach(customerList ->
        {
            if (customerDAO.equals(customerList)){
                returnFlag.set(true);
            }
        });
        //        System.out.println("RETURN FLAG" + true);
        return returnFlag.get();
    }
    /**@return return true if a entry with the @param ID in the h2 database exist**/
    public boolean existByID (long id){
        return customerDAO.existsById(id);
    }
}
