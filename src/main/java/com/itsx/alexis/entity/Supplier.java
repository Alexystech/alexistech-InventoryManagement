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
public class Supplier {

    @Id
    @Column(name = "id_supplier")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSupplier;

    @Column(name = "name_supplier",length = 50)
    private String nameSupplier;
}
