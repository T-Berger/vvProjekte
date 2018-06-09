package vv.restwebservice.dao;


import org.springframework.stereotype.Repository;
import vv.restwebservice.dao.interfacesDAO.IContractDAO;
import vv.restwebservice.modells.Contract;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

// @Transactional
@Repository
public class ContractDAO implements IContractDAO {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Contract getContractById(int contractId) {
        return entityManager.find(Contract.class, contractId);
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Contract> getAllContracts() {
        String hql = "FROM Contract as atcl ORDER BY atcl.contractId";
        return (List<Contract>) entityManager.createQuery(hql).getResultList();
    }
    @Override
    public void addContract(Contract contract) {
        entityManager.persist(contract);
    }
    @Override
    public void updateContract(Contract contract) {
        Contract cont = getContractById(contract.getContractId());
        cont.setPrice(contract.getPrice());
        cont.setType(contract.getType());
        cont.setCustomer(contract.getCustomer());
        entityManager.flush();
    }
    @Override
    public void deleteContract(int contractId) {
        entityManager.remove(getContractById(contractId));
    }
//    @Override
//    public boolean contractExists(String title, String category) {
//        String hql = "FROM Contract as atcl WHERE atcl.title = ? and atcl.category = ?";
//        int count = entityManager.createQuery(hql).setParameter(1, title)
//                .setParameter(2, category).getResultList().size();
//        return count > 0 ? true : false;
//    }
}
