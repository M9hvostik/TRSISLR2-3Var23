package info.Lr2_3Var23.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.Lr2_3Var23.db.model.Fridge;
import lombok.extern.slf4j.Slf4j;
import info.Lr2_3Var23.db.dao.FridgeRepository;
import info.Lr2_3Var23.db.model.Product;
import java.util.Optional;


@Slf4j
@Service
public class FridgeServiceImpl implements FridgeService {

    @Autowired
    private FridgeRepository fridgeRepository;

    @Override
    public Iterable<Fridge> listAll() {
        return fridgeRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        fridgeRepository.deleteById(id);
    }

    @Override
    public Fridge add(String name) {
        return fridgeRepository.save(new Fridge(name));
    }

    @Override
    public Optional<Fridge> findById(Integer id) {
        return fridgeRepository.findById(id);
    }
    
    @Override
    public Fridge findByName(String name) {
        return fridgeRepository.findByName(name);
    }
//    @Override
//    public Fridge findByNameAndProductionsListId(String name, Integer id){
//        return fridgeRepository.findByNameAndProductionsListId(name, id);
//    }
    
    @Override
    public Fridge save(Fridge fridge) {
        return fridgeRepository.save(fridge);
    }


    @Override
    public boolean existsById(Integer id) {
        return fridgeRepository.existsById(id);
    }
    
    @Override
    public Product getProduct(String name, Integer id){
        return fridgeRepository.getProduct(name, id);
    }
    @Override
    public void deleteProductFromFridge(Integer id){
        fridgeRepository.deleteProductFromFridge(id);
    }
}
