package com.self.ecomproject.services;

import com.self.ecomproject.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    public Product getProductById(Long id);
    public List<Product> getAllProducts();
    public Product replaceProduct(Long id, Product product);
}
