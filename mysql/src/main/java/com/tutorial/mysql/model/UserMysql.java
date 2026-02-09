package com.tutorial.mysql.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Persons")
@ToString(exclude = "addresses")
public class UserMysql {

    @Id
    @Column(name = "PersonID")
    private Integer personId;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "Address")
    private String address;

    @Column(name = "City")
    private String city;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserAddress> addresses = new ArrayList<>();
}
