package services;

import dao.interfacesDAO.IContractDAO;
import modells.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import services.interfacesService.IContractService;

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
