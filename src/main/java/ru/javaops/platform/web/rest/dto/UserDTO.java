package ru.javaops.platform.web.rest.dto;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class UserDTO {

    @Email
    @NotNull
    @Size(min = 5, max = 100)
    private String login;

    @NotNull
    @Size(min = 5, max = 100)
    private String password;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Size(min = 3, max = 50)
    private String location;

    @Size(max = 100)
    private String whereLeanedAboutCourse;

    @Size(max = 12)
    private String phone;

    @Size(max = 50)
    private String skype;

    @Size(min = 2, max = 5)
    private String langKey;

    private List<String> roles;

    public UserDTO() {
    }

    public UserDTO(String login, String password, String firstName, String lastName, String location,
                   String whereLeanedAboutCourse, String phone, String skype, String langKey, List<String> roles) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.whereLeanedAboutCourse = whereLeanedAboutCourse;
        this.phone = phone;
        this.skype = skype;
        this.langKey = langKey;
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLangKey() {
        return langKey;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getLocation() {
        return location;
    }

    public String getWhereLeanedAboutCourse() {
        return whereLeanedAboutCourse;
    }

    public String getPhone() {
        return phone;
    }

    public String getSkype() {
        return skype;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
        "  login='" + login + '\'' +
        ", password='" + password + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", langKey='" + langKey + '\'' +
        ", roles=" + roles + '\'' +
        ", location='" + location + '\'' +
        ", whereLeanedAboutCourse='" + whereLeanedAboutCourse + '\'' +
        ", phone='" + phone + '\'' +
        ", skype='" + skype +
        '}';
    }
}
