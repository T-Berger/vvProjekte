package vv.restwebservice.dao;


import org.springframework.stereotype.Repository;
import vv.restwebservice.dao.interfacesDAO.ICustomerDAO;
import vv.restwebservice.modells.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CustomerDAO implements ICustomerDAO {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Customer getCustomerById(int customerId) {
        return entityManager.find(Customer.class, customerId);
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Customer> getAllCustomers() {
        String hql = "FROM Customer as atcl ORDER BY atcl.customerId";
        return (List<Customer>) entityManager.createQuery(hql).getResultList();
    }
//    @Override
//    public void addCustomer(Customer customer) {
//        entityManager.persist(customer);
//    }
    @Override
    public void updateCustomer(Customer customer) {
        Customer cstmer = getCustomerById(customer.getCustomerId());
        cstmer.setAddress(customer.getAddress());
        cstmer.setDescription(cstmer.getDescription());
        cstmer.setBirthday(cstmer.getBirthday());
        cstmer.setForename(cstmer.getForename());
        cstmer.setSurname(cstmer.getSurname());
        entityManager.flush();
    }
    @Override
    public void deleteCustomer(int customerId) {
        entityManager.remove(getCustomerById(customerId));
    }
//    @Override
//    public boolean customerExists(String title, String category) {
//        String hql = "FROM Customer as atcl WHERE atcl.title = ? and atcl.category = ?";
//        int count = entityManager.createQuery(hql).setParameter(1, title)
//                .setParameter(2, category).getResultList().size();
//        return count > 0 ? true : false;
//    }
}
