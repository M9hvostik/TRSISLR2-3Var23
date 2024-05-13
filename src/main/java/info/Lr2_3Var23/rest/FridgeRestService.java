package info.Lr2_3Var23.rest;

import info.Lr2_3Var23.db.model.Fridge;
import info.Lr2_3Var23.db.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import info.Lr2_3Var23.service.FridgeService;
import info.Lr2_3Var23.service.ProductService;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@RequestMapping("/public/rest/fridges")
public class FridgeRestService {

    @Autowired
    private FridgeService fridgeService;

    @Autowired
    private ProductService productService;
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Object> browse() {
        return ResponseEntity.ok(fridgeService.listAll());
    }
    
    @RequestMapping(value = "/{name}",method = RequestMethod.GET)
    public ResponseEntity<Object> findByName(@PathVariable("name") String name) {
        Fridge fridge = fridgeService.findByName(name);
        if(fridge == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fridge not found");
        return ResponseEntity.ok(fridge);
    }
 
    @RequestMapping(value = "/{name}/{productId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getProduct(@PathVariable("name") String name, @PathVariable("productId") Integer productId) {
       
        Product product = fridgeService.getProduct(name, productId);
        if(product == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found in this fridge");
        
        return ResponseEntity.ok(product);
    }
    
    @RequestMapping(value = "/{name}/{productId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> putExistProduct(@PathVariable("name") String name, @PathVariable("productId") Integer productId) {
       
       Fridge fridge = fridgeService.findByName(name);
        if(fridge == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fridge not found");
        Optional<Product> productOP = productService.findById(productId);
        if(!productOP.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        
        Product product = productOP.get();
        product.setFridge(fridge.getId());
       
        
        return ResponseEntity.ok(productService.save(product));
    }
    
    @RequestMapping(value = "/{name}", headers = "content-type=application/json", method = RequestMethod.PUT)
    public ResponseEntity<Object> putProduct(@PathVariable("name") String name, @RequestBody Product product) {
       
        Fridge fridge = fridgeService.findByName(name);
        if(fridge == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fridge not found");
                
        product.setFridge(fridge.getId());
             
        
        return ResponseEntity.ok(productService.save(product));
    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) {
        fridgeService.delete(id);
    }
    
    @RequestMapping(value = "/productDEL/{productId}", method = RequestMethod.DELETE)
    public void deleteProductFromFridge(@PathVariable("productId") Integer productId) {
        fridgeService.deleteProductFromFridge(productId);
    }
    

    @RequestMapping(value = "/{name}", method = RequestMethod.POST)
    public ResponseEntity<Object> add(@PathVariable("name") String name) {
        Fridge fridge = fridgeService.findByName(name);
        if(fridge != null)
            return ResponseEntity.status(HttpStatus.FOUND).body("Fridge with that name already exists");
        return ResponseEntity.ok(fridgeService.add(name));
    }
    
   
}
