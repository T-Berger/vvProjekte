package vv.restwebservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vv.restwebservice.dao.interfacesDAO.IContractDAO;
import vv.restwebservice.modells.Contract;
import vv.restwebservice.services.interfacesService.IContractService;

import java.util.List;

@Service
public class ContractService implements IContractService {
    @Autowired
    private IContractDAO contractDAO;
    @Override
    public Contract getContractById(int contractId) {
        Contract obj = contractDAO.getContractById(contractId);
        return obj;
    }
    @Override
    public List<Contract> getAllContracts(){
        return contractDAO.getAllContracts();
    }
    @Override
    public synchronized boolean addContract(Contract contract){
//        if (contractDAO.contractExists(contract.getTitle(), contract.getCategory())) {
//            return false;
//        } else {
//            contractDAO.addContract(contract);
//            return true;
//        }
        return true;
    }
    @Override
    public void updateContract(Contract contract) {
        contractDAO.updateContract(contract);
    }
    @Override
    public void deleteContract(int contractId) {
        contractDAO.deleteContract(contractId);
    }
}
