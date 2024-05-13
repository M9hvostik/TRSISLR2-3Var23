package info.Lr2_3Var23.rest;

import info.Lr2_3Var23.db.model.Fridge;
import info.Lr2_3Var23.db.model.Product;
import info.Lr2_3Var23.service.FridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import info.Lr2_3Var23.service.ProductService;

import java.util.Date;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

@Slf4j
@RestController
@RequestMapping("/public/rest/products")
public class ProductRestService {

    private final String formatDate = "yyyy-mm-dd";
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private FridgeService fridgeService;
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Object> browse() {
        return ResponseEntity.ok(productService.listAll());
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(productService.findById(id));
    }
    
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<Object> findByName(@PathVariable("name") String name) {
        Iterable<Product> products = productService.findByName(name);
        if(products == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Products not found");
        return ResponseEntity.ok(products);
    }
    
    @RequestMapping(value = "/fridge/{fridgeId}", method = RequestMethod.GET)
    public ResponseEntity<Object> findByFridge(@PathVariable("fridgeId") Integer id) {
        return ResponseEntity.ok(productService.findByFridge(id));
    }
    
    @RequestMapping(value = "/fridge/{fridgeId}/{name}", method = RequestMethod.GET)
    public ResponseEntity<Object> findByFridgeAndName(@PathVariable("fridgeId") Integer id,@PathVariable("name") String name) {
        return ResponseEntity.ok(productService.findByFridgeAndName(id,name));
    }
    @RequestMapping(value = "/{id}/{fridgeId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> putProduct(@PathVariable("id") Integer id, @PathVariable("fridgeId") Integer fridgeId) {
        
        if(!fridgeService.existsById(fridgeId))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fridge not found");
        Optional<Product> productOP = productService.findById(id);
        if(!productOP.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        
        Product product = productOP.get();
        product.setFridge(fridgeId);
        
        return ResponseEntity.ok(productService.save(product));
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) {
        productService.delete(id);
    }
//String name, Date productionDate, Date expirationDate
    @RequestMapping(value = "/{name}/{productionDate}/{expirationDate}", method = RequestMethod.POST)
    public ResponseEntity<Object> add(@PathVariable("name") String name, @PathVariable("productionDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date productionDate, @PathVariable("expirationDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date  expirationDate) {
        return ResponseEntity.ok(productService.add(name, productionDate, expirationDate));
    }
    
    @RequestMapping(value = "/{name}/{productionDate}/{expirationDate}/{fridgeId}", method = RequestMethod.POST)
    public ResponseEntity<Object> add(@PathVariable("name") String name, @PathVariable("productionDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date productionDate, @PathVariable("expirationDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date  expirationDate, @PathVariable("fridgeId") Integer fridgeId) {
        return ResponseEntity.ok(productService.add(name, productionDate, expirationDate, fridgeId));
    }
}
