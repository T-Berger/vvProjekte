package vv.restwebservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vv.restwebservice.dao.interfacesDAO.IContractDAO;
import vv.restwebservice.modells.Contract;
import vv.restwebservice.services.interfacesService.IContractService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ContractService implements IContractService {
    @Autowired
    private IContractDAO contractDAO;
    @Override
    public Contract getContractById(long contractId) {
        Contract obj = contractDAO.getContractById(contractId);
        Contract c =obj;
        c.setCustomer(obj.getCustomer());
//        System.out.println(obj.toString());obj.getCustomer().getid()
        return obj;
    }
//    @Override
//    public List<Contract> getAllContracts(){
//        return contractDAO.getAllContracts();
//    }
    @Override
    public synchronized boolean addContract(Contract contract){
//        if (contractDAO.contractExists(contract.getTitle(), contract.getCategory())) {
//            return false;
//        } else {
//            contractDAO.addContract(contract);
//            return true;
//        }
//        return true;
        contractDAO.save(contract);
        return true;

    }
    @Override
    public void updateContract(Contract contract) {
        System.out.println(contract);
        Contract updatedContract = getContractById(contract.getContractId());
        updatedContract.setForeignKey(contract.getForeignKey());
        updatedContract.setCustomer(contract.getCustomer());
//        updatedContract.setCustomer(new CustomerService().getCustomerById(contract.getForeignKey()));
        updatedContract.setId(contract.getContractId());
        updatedContract.setPrice(contract.getPrice());
        updatedContract.setSurname(contract.getSurname());
        updatedContract.setType(contract.getType());
        System.out.println(updatedContract.toString());
        contractDAO.save(updatedContract);
    }
//    @Override
    public void deleteContract(Long contractId) {
//        contractDAO.deleteContract(contractId);
        contractDAO.delete(contractDAO.findById(contractId).get());
    }
}
