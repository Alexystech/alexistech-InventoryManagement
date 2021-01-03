package com.itsx.alexis.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    @NotNull(message = "campo obligatorio")
    @ManyToOne
    @JoinColumn(name = "fk_id_category", referencedColumnName = "id_category", nullable = false)
    private Category category;

    @NotEmpty(message = "campo obligatorio")
    @Min(value = 3, message = "nombre de producto demaciado corto")
    @Max(value = 50, message = "nombre de producto demaciado largo")
    @Column(length = 50)
    private String name;

    @NotNull(message = "campo obligatorio")
    @Min(value = 0, message = "el precio minimo es 0 pesos MXN")
    private double price;

    @NotNull(message = "campo obligatorio")
    @Min(value = 1, message = "la cantidad de existencia minima es de 1")
    private int amount;

    @NotNull(message = "campo obligatorio")
    @ManyToOne
    @JoinColumn(name = "fk_id_supplier", referencedColumnName = "id_supplier", nullable = false)
    private Supplier supplier;

}
