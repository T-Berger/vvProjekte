package vv.restwebservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vv.restwebservice.dao.interfacesDAO.IContractDAO;
import vv.restwebservice.modells.Contract;
import vv.restwebservice.services.interfacesService.IContractService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
//@Version privat int Version

@Service
@Transactional
public class ContractService implements IContractService {
    @Autowired
    private IContractDAO contractDAO;
    @Override
    public Contract getContractById(long contractId) {
        if (contractDAO.existsById(contractId)){
            Contract obj = contractDAO.getContractById(contractId);
            Contract c =obj;

            c.setCustomer(obj.getCustomer());
    //        System.out.println(obj.toString());obj.getCustomer().getid()
            return obj;
        }
        return null;
    }
    // @Override
    public List<Contract> getAllContracts(){
        List<Contract> contracts = new ArrayList<>();
        contractDAO.findAll().forEach(contracts::add);
        return  contracts;
    }
    public boolean contractExists(Contract contract){
        AtomicBoolean returnFlag = new AtomicBoolean(false);
        getAllContracts().forEach(contractList ->
        {
            if (contract.equals(contractList)){
                returnFlag.set(true);
            }
        });
        return returnFlag.get();
    }
    @Override
    public synchronized boolean addContract(Contract contract){
        if (contractExists(contract)) {
            return false;
        } else {
//            contractDAO.addContract(contract);
            contractDAO.save(contract);
            return true;
        }

    }
    @Override
    @Transactional
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
    public boolean deleteContract(Contract contract){
        if (contractExists(contract)) {
            Contract found = getContractByName(contract);
            deleteContract(found.getContractId());
            return true;
        } else {
            return false;
        }
    }
    public Contract getContractByEquals(Contract contract){
//        List<Contract> contracts = new ArrayList<>();
//        contractDAO.findAll().forEach(contracts::add);
//        return  contracts;
        AtomicReference<Contract> c = new AtomicReference<>(new Contract());
        contractDAO.findAll().forEach(contract1 -> {if(contract.equals(contract1)){
            c.set(contract1);
        }});
        return c.get();
    }
    private Contract getContractByName(Contract contract) {
//        if (!contractExists(contract)) return
        AtomicReference<Contract> c = null;
        contractDAO.findAll().forEach(contract1 -> {
            if (contract.equals(contract1)) {
                c.set(contract1);
            }
        });
        return c.get();
    }
}
