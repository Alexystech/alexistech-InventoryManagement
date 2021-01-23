package com.itsx.alexis.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Administrator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "campo obligatorio")
    @Size(min = 3, max = 40, message = "el username debe contener entre 3 y 40 caracteres")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "el nombre de usuario no puede contener espacios o caracteres especiales")
    @Column(name = "user_name", length = 40)
    private String userName;

    @NotEmpty(message = "campo obligatorio")
    @Size(min = 3, max = 20, message = "nombre debe contener entre 3 y 20 caracteres")
    @Column(length = 20)
    private String name;

    @NotEmpty(message = "campo obligatorio")
    @Size(min = 3, max = 20, message = "apellido paterno debe contener entre 3 y 20 caracteres")
    @Column(name = "last_name", length = 20)
    private String lastName;

    @NotEmpty(message = "campo obligatorio")
    @Size(min = 3, max = 20, message = "apellido materno debe contener entre 3 y 20 caracteres")
    @Column(name = "mother_last_name", length = 20)
    private String motherLastName;

    @NotEmpty(message = "campo obligatorio")
    @Email
    @Column(length = 30)
    private String eMail;

    @NotNull(message = "campo obligatorio")
    @Size(min = 8, max = 50, message = "La contrasenia debe contener entre 8 y 50 caracteres")
    @Column(length = 50)
    private String password;

    @NotEmpty(message = "campo obligatorio")
    @Size(min = 10, max = 10, message = "el numero estrictamente debe contener 10 digitos")
    @Pattern(regexp = "[0-9]+", message = "el numero telefonico solo puede contener caracteres numericos")
    @Column(name = "cell_phone", length = 10)
    private String cellPhone;
}
