package com.restaurant.app.Employee.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restaurant.app.User.entity.User;
import com.restaurant.app.common.bases.Person;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name="employees")
@EqualsAndHashCode(callSuper = false)
public class Employee extends Person
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;
}
