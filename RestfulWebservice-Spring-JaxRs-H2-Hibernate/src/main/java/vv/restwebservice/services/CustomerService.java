package vv.restwebservice.services;


import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import vv.restwebservice.dao.interfacesDAO.ICustomerDAO;
import vv.restwebservice.modells.Customer;
import vv.restwebservice.services.interfacesService.ICustomerService;

import java.util.List;

@org.springframework.stereotype.Service
@Transactional
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerDAO customerDAO;
    @Override
    public Customer getCustomerById(long id) {
//        Customer obj = customerDAO.getCustomerById(id);
        Customer obj = customerDAO.findById(id).get();
        return obj;
        //////////////////////////////////////////////////////////////////////Falls ID nicht gefunden wird
    }
//    @Override
//    public List<Customer> getAllCustomers(){
//        return customerDAO.getAllCustomers();
//    }
    @Override
    public synchronized boolean addCustomer(Customer customer){
        /////DAS HIER
//        if (customerDAO.customerExists(customer.getTitle(), customer.getCategory())) {
//            return false;
//        } else {
//            customerDAO.addCustomer(customer);
//            return true;
//        }
        System.out.println(customer.getContracts());
        System.out.println(customer.getContracts().get(1));
        System.out.println(customer.getContracts().get(1).getCustomer());

        Customer c = customer;
//        boolean test= new ContractService().addContract(customer.getContracts().get(1));
//        Customer c = customerDAO.save(customer);
        customerDAO.save(customer);
        // MAP contracts to Customer
        System.out.println(c);
        c.getContracts().forEach(contract -> {
            contract.setCustomer(c);
            contract.setForeignKey(c.getid());
        }) ;
        System.out.println(c.getContracts());
        System.out.println(c.getContracts().get(1).getCustomer());
        customerDAO.save(c);
        return true;
    }
    @Override
    public void updateCustomer(Customer customer) {
        Customer updatedCustomer = getCustomerById(customer.getid());
        updatedCustomer.setAddress(customer.getAddress());
        updatedCustomer.setContracts(customer.getContracts());
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
}
