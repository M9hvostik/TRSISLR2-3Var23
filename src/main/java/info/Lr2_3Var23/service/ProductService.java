package info.Lr2_3Var23.service;

import info.Lr2_3Var23.db.model.Product;
import java.util.Date;
import java.util.Optional;

public interface ProductService {

    Iterable<Product> listAll();

    void delete(Integer id);
    
    Product add(String name, Date productionDate, Date expirationDate);
    Product add(String name, Date productionDate, Date expirationDate, Integer fridge);
    Optional<Product> findById(Integer id);
    Iterable<Product> findByName(String name);
    Iterable<Product> findByFridge(Integer fridge);
    Iterable<Product> findByFridgeAndName(Integer fridge, String name);
    
    Product save(Product product);
    boolean existsById(Integer id);
}
