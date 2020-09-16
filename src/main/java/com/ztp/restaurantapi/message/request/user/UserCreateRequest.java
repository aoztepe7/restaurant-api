package com.ztp.restaurantapi.message.request.user;

import com.ztp.restaurantapi.domain.user.Role;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserCreateRequest {
    @NotEmpty(message = "First Name Can Not Be Empty!")
    @Length(min = 3, message = "Your First Name Must Contain Minimum 3 Characters")
    private String firstName;

    @NotEmpty(message = "Last Name Can Not Be Empty!")
    @Length(min = 2, message = "Your Last Name Must Contain Minimum 2 Characters")
    private String lastName;

    @NotEmpty(message = "E-Mail Address Can Not Be Empty!")
    @Length(min = 7, message = "E-Mail Address Must Contain Minimum 7 Characters")
    @Email(message = "Please Make Sure That Your Mail Address Contains '@' and domain" )
    private String mail;

    @NotEmpty(message = "Password Can Not Be Empty!")
    @Length(min = 8, message = "Password Must Contain Minimum 8 Characters")
    private String password;

    @NotEmpty(message = "Repeat Password Can Not Be Empty!")
    @Length(min = 8, message = "Repeat Password Must Contain Minimum 8 Characters")
    private String repeatedPassword;

    @NotNull
    private Role role;
}
