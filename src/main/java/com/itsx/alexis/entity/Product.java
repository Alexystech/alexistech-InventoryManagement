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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduct;

    @ManyToOne
    @JoinColumn(name = "fk_id_category", referencedColumnName = "id_category",nullable = false)
    private Category category;

    @Column(length = 50)
    private String name;

    private double price;
    private int amount;

    @ManyToOne
    @JoinColumn(name = "fk_id_supplier", referencedColumnName = "id_supplier",nullable = false)
    private Supplier supplier;
}
