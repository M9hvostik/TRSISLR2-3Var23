package info.Lr2_3Var23.db.dao;

import info.Lr2_3Var23.db.model.Fridge;
import info.Lr2_3Var23.db.model.Product;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Pavel
 */
public interface FridgeRepository extends CrudRepository<Fridge, Integer> {

     public Fridge findByName(String name);
     
    //public Fridge findByNameAndProductionsListId(String name, Integer id);
    @Query("select production1_ from\n" +
"        Fridge fridgeT \n" +
"    left outer join\n" +
"        Product production1_ \n" +
"            on fridgeT.id=production1_.fridge\n" +
"    where\n" +
"        fridgeT.name=?1 \n" +
"        and production1_.id=?2")
    public Product getProduct(String name, Integer id);
    
    @Transactional
    @Modifying
    @Query("update Product pr set pr.fridge=NULL WHERE pr.id=?1")
    public void deleteProductFromFridge(Integer id);
}
