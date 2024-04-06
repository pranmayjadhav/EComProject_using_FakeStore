package com.self.ecomproject.services;

import com.self.ecomproject.dtos.FakeStoreProductDTO;
import com.self.ecomproject.models.Category;
import com.self.ecomproject.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
        FakeStoreProductDTO[] fakeStoreProductDTOs =
                restTemplate.getForObject("https://fakestoreapi.com/products",
                FakeStoreProductDTO[].class); //used Array due to type erasure

        //System.out.println("Debug"); //to check response type

        //Convert List of FakeStoreProductDTO into List of Products.
        List<Product> response = new ArrayList<>();
        for (FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductDTOs) {
            response.add(covertFakeStoreProductDtoToProduct(fakeStoreProductDTO));
        }
        return response;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setImage(product.getImage());
        fakeStoreProductDTO.setDesc(product.getDescription());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDTO, FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDTO.class, restTemplate.getMessageConverters());
        FakeStoreProductDTO response =  restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);

        return covertFakeStoreProductDtoToProduct(response);
    }
}
