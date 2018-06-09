package vv.restwebservice.dao.interfacesDAO;

import vv.restwebservice.modells.Contract;

import java.util.List;

public interface IContractDAO {
    List<Contract> getAllContracts();
    Contract getContractById(int contractId);
//    boolean addContract(Contract contract);
    void addContract(Contract contract);
    void updateContract(Contract contract);
    void deleteContract(int contractId);
}
