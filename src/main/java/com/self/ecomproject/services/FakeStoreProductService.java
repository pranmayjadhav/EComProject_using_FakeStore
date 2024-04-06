package com.self.ecomproject.services;

import com.self.ecomproject.dtos.FakeStoreProductDTO;
import com.self.ecomproject.models.Category;
import com.self.ecomproject.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private Product covertFakeStoreProductDtoToProduct(FakeStoreProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDesc());
        product.setImage(dto.getImage());

        Category category = new Category();
        category.setDescription(dto.getDesc());

        product.setCategory(category);

        return product;
    }
    @Override
    public Product getProductById(Long id) {
        //Call FakeStore API here to get product with given ID
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                FakeStoreProductDTO.class);
        //first param -> URL
        //second param -> Response

        //Convert FakeStoreProductDTO into Product object.
        if (fakeStoreProductDTO == null) {
            return null;
        }
        return covertFakeStoreProductDtoToProduct(fakeStoreProductDTO);
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }
}
