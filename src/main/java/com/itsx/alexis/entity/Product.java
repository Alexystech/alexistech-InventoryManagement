package com.itsx.alexis.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @Column(name = "id_product")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idProduct;

    @JoinColumn(name = "fk_id_category",nullable = false)
    @ManyToOne
    private Category category;

    @Column(length = 50)
    private String name;

    private double price;
    private int amount;

    @JoinColumn(name = "fk_id_supplier",nullable = false)
    @ManyToOne
    private Supplier supplier;
}
