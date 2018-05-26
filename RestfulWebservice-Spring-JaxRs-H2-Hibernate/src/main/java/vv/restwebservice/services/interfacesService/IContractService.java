package services.interfacesService;

import modells.Contract;

import java.util.List;

public interface IContractService {
    List<Contract> getAllContracts();
    Contract getContractById(int contractId);
    boolean addContract(Contract contract);
    void updateContract(Contract contract);
    void deleteContract(int contractId);
}
