package com.itsx.alexis.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
    @Size(min = 3, max = 50, message = "el nombre debe contener entre 3 y 50 caracteres")
    @Column(name = "name_supplier", length = 50)
    private String nameSupplier;

}
