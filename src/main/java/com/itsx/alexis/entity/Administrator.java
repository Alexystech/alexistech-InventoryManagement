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
public class Administrator {

    @Id
    @Column(name = "id_administrator")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idAdministrator;

    @Column(name = "user_name",length = 40)
    private String userName;

    @Column(length = 20)
    private String name;

    @Column(name = "last_name",length = 20)
    private String lastName;

    @Column(name = "mother_last_name",length = 20)
    private String motherLastName;

    @Column(length = 50)
    private String password;

    @Column(name = "cell_phone",length = 10)
    private String cellPhone;
}
