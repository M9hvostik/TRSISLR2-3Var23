package info.Lr2_3Var23.service;

import info.Lr2_3Var23.db.model.Fridge;
import info.Lr2_3Var23.db.model.Product;
import java.util.Optional;

public interface FridgeService {

    Iterable<Fridge> listAll();

    void delete(Integer id);
    
    Fridge add(String name);
    
    Optional<Fridge> findById(Integer id);
    Fridge findByName(String name);
    //Fridge findByNameAndProductionsListId(String name, Integer id);
    
    Fridge save(Fridge fridge);

    boolean existsById(Integer id);
    Product getProduct(String name, Integer id);
    void deleteProductFromFridge(Integer id);
}
