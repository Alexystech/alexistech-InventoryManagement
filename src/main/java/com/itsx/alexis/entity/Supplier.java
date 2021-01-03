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
public class Supplier {

    @Id
    @Column(name = "id_supplier")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSupplier;

    @NotEmpty(message = "campo obligatorio")
    @Min(value = 3,message = "nombre de proveedor demaciado corto")
    @Max(value = 50,message = "nombre de proveedor demaciado largo")
    @Column(name = "name_supplier", length = 50)
    private String nameSupplier;

}
