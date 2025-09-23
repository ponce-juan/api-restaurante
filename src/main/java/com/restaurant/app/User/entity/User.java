package com.restaurant.app.User.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restaurant.app.Company.entity.Company;
import com.restaurant.app.Employee.entity.Employee;
import jakarta.persistence.*;
import lombok.*;

//Comandos extras
// @NoArgsConstructor -> Construye el constructor de la clase sin los argumentos
// @RequiredArgsConstructor -> Construye el constructor con argumentos especificos
// @AllArgsConstructor -> Genera el constructor con todos los argumentos
// @NonNull -> Indica que el argumento no puede ser nulo, ya que se utiliza en el constructor

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@ToString(exclude = {"employee", "company"}) // Evita el bucle al imprimir el objeto
public class User
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String username;

    @NonNull
    private String password;

    //Relacion uno a uno con Employee
    @OneToOne(mappedBy = "user")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id", referencedColumnName = "id")
    @JsonIgnore
    private Company company;

}
