package com.example.quiz_app_backend.Entity;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String password;
    private long phno;
    private String address;

    public UserDetails(long id, String firstName, String lastName, String gender, String email, String password, long phno, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.phno = phno;
        this.address = address;
    }

    public UserDetails() {
    }

    public UserDetails(String firstName, String lastName, String gender, String email, long phno, String password, String address) {
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phno=" + phno +
                ", address='" + address + '\'' +
                '}';
    }
}
