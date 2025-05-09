package com.hcl.academy.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String associateId;
    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;
    private String email;
    private String contactNumber;
    private String password;
}
