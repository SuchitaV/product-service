package com.product.service;

import com.product.repository.ProductRepository;
import com.product.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Product addProduct(Product product) {
        return repository.save(product);
    }

    public Product updateProduct(Long id, Product newProduct) {
        return repository.findById(id)
                .map(p -> {
                    p.setName(newProduct.getName());
                    p.setPrice(newProduct.getPrice());
                    p.setQuantity(newProduct.getQuantity());
                    return repository.save(p);
                })
                .orElse(null);
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }
}
