package vv.restwebservice.dao.interfacesDAO;

import org.springframework.data.repository.CrudRepository;
import vv.restwebservice.modells.Contract;

import java.util.List;

public interface IContractDAO
        extends CrudRepository<Contract,Long> {
//    List<Contract> getAllContracts();
    Contract getContractById(long contractId);
//    boolean addContract(Contract contract);
//    void addContract(Contract contract);
//    void updateContract(Contract contract);
//    void deleteContract(int contractId);
}
