package com.self.ecomproject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product {
    @Id
    private Long id;
    private String title;
    private String description;
    private double price;
    private String image;
    @ManyToOne
    private Category category;
}
