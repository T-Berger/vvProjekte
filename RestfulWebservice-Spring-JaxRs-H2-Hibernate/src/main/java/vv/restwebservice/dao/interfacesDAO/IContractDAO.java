package vv.restwebservice.dao.interfacesDAO;

import org.springframework.data.repository.CrudRepository;
import vv.restwebservice.modells.Contract;

public interface IContractDAO
        extends CrudRepository<Contract,Long> {
//    List<Contract> getAllContracts();
    Contract getContractById(long contractId);

//    @Query("SELECT p FROM Person p WHERE LOWER(p.lastName) = LOWER(:lastName)")
//    Contract List<Contract> find(@Param("lastName") String lastName);
//    boolean addContract(Contract contract);
//    void addContract(Contract contract);
//    void updateContract(Contract contract);
//    void deleteContract(int contractId);
}
