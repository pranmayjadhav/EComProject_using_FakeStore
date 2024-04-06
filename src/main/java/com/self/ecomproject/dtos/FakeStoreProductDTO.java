package com.self.ecomproject.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private double price;
    private String desc;
    private String image;
    private String category;

}
