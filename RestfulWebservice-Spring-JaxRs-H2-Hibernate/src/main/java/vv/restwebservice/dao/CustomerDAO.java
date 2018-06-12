//package vv.restwebservice.dao;
//
//
//import org.springframework.stereotype.Repository;
//import vv.restwebservice.dao.interfacesDAO.ICustomerDAO;
//import vv.restwebservice.modells.Customer;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
//@Repository
//public class CustomerDAO implements ICustomerDAO {
//    @PersistenceContext
//    private EntityManager entityManager;
//    @Override
//    public Customer getCustomerById(int id) {
//        return entityManager.find(Customer.class, id);
//    }
//    @SuppressWarnings("unchecked")
//    @Override
//    public List<Customer> getAllCustomers() {
//        String hql = "FROM Customer as atcl ORDER BY atcl.id";
//        return (List<Customer>) entityManager.createQuery(hql).getResultList();
//    }
//    @Override
//    public void addCustomer(Customer customer) {
//        entityManager.persist(customer);
//    }
//    @Override
//    public void updateCustomer(Customer customer) {
//        Customer cstmer = getCustomerById(customer.getid());
//        cstmer.setAddress(customer.getAddress());
//        cstmer.setDescription(customer.getDescription());
//        cstmer.setBirthday(customer.getBirthday());
//        cstmer.setForename(customer.getForename());
//        cstmer.setSurname(customer.getSurname());
//        cstmer.setContracts(customer.getContracts());
//        entityManager.flush();
//    }
//    @Override
//    public void deleteCustomer(int id) {
//        entityManager.remove(getCustomerById(id));
//    }
////    @Override
////    public boolean customerExists(String title, String category) {
////        String hql = "FROM Customer as atcl WHERE atcl.title = ? and atcl.category = ?";
////        int count = entityManager.createQuery(hql).setParameter(1, title)
////                .setParameter(2, category).getResultList().size();
////        return count > 0 ? true : false;
////    }
//}
