package com.self.ecomproject.services;

import com.self.ecomproject.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class FakeStoreProductService implements ProductService{
    @Override
    public Product getProductById(Long id) {
        //Call FakeStore API here to get product with given ID
        RestTemplate restTemplate = new RestTemplate();
        return new Product();
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }
}
