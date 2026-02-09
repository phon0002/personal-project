package com.tutorial.model.mysql;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Persons")
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
}