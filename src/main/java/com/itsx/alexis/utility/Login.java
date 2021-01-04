package com.itsx.alexis.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Login {

    @NotEmpty(message = "campo vacio")
    private String userName;

    @NotEmpty(message = "campo vacio")
    private String password;
}
