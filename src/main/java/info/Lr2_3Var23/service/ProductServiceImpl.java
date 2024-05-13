package info.Lr2_3Var23.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.Lr2_3Var23.db.model.Product;
import lombok.extern.slf4j.Slf4j;
import info.Lr2_3Var23.db.dao.ProductRepository;
import java.util.Date;
import java.util.Optional;


@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Iterable<Product> listAll() {
        return productRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product add(String name, Date productionDate, Date expirationDate) {
        return productRepository.save(new Product(name, productionDate, expirationDate));
    }
    
    @Override
    public Product add(String name, Date productionDate, Date expirationDate, Integer fridge) {
        return productRepository.save(new Product(name, productionDate, expirationDate, fridge));
    }
    
    @Override
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }
    
    @Override
    public Iterable<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Iterable<Product> findByFridge(Integer fridge) {
        return productRepository.findByFridge(fridge);
    }

    @Override
    public Iterable<Product> findByFridgeAndName(Integer fridge, String name) {
        return productRepository.findByFridgeAndName(fridge, name);
    }
    
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
    
    @Override
    public boolean existsById(Integer id) {
        return productRepository.existsById(id);
    }
}
