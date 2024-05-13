package info.Lr2_3Var23.db.dao;

import info.Lr2_3Var23.db.model.Fridge;
import info.Lr2_3Var23.db.model.Product;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Pavel
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {

     public List<Product> findByFridge(Integer fridge);
     public List<Product> findByFridgeAndName(Integer fridge, String name);
     public List<Product> findByName(String name);
}
