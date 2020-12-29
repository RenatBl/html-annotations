package ru.itis.html_annotations.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.html_annotations.annotations.HtmlForm;
import ru.itis.html_annotations.annotations.HtmlInput;

@Data
@AllArgsConstructor
@NoArgsConstructor
@HtmlForm
public class User {

    @HtmlInput(name = "login", placeholder = "Enter username")
    private String login;

    @HtmlInput(name = "password", type = "password", placeholder = "Enter password")
    private String password;

    @HtmlInput(name = "phone", placeholder = "Enter ")
    private String phone;

    @HtmlInput(name = "email", placeholder = "Enter email")
    private String email;

    @HtmlInput(name = "age", placeholder = "Enter age")
    private Integer age;
}
