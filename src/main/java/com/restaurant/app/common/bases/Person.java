package com.restaurant.app.common.bases;

import com.restaurant.app.common.embedded.Address;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//@MappedSuperclass <- Consultar al profesor para que sirve
// y cuando se usa

/*

Bibliotecas que se pueden utilizar para el proyecto
* swagger -> biblioteca para documentar la API (se puede utilizar con properties, json, yaml, Annotations)
            crea una pagina para utilizar la API

* liquibase -> biblioteca para manejar la base de datos, versionado, cambios de la basae de datos
               actualizacion automatica de la base de datos. Utiliza jdbc, sirve para migrar de una base de datos a otra

* flyway -> administracion de la base de datos

Herramienta de loggin de traza o rastreo (logging) <- log4j, log4j2;
Spring utiliza backbone para el loggin

Se puede utilizar la notacion @slf4j para el loggin de la aplicacion, ya incluida en lombok
Ej de utilizacion:
logging.info("Mensaje de log info");
loggin.debug("Mensaje de log debug");
loggin.error("Mensaje de log error");

A traves del application.properties se puede habilitar el logging por niveles
por paquete o clase, para diferenciar rama de testing, desarrollo o produccion.


Mapeo de herencia
La notacion @MappedSuperclass se utiliza para indicar que la clase es una clase base
y los atributos de la clase base se heredaran en las clases hijas y se persisten en la tabla

Si no se utiliza la notacion @MappedSuperclass, los atributos de la clase base no se heredaran, por lo tanto, no
persisten en la tabla de los hijos

Clase base: Persona -> nombre, apellido, telefono, email

Clase hija: Paciente -> nombre, apellido, telefono, email
Clase hija: Empleado -> nombre, apellido, telefono, email, salario

* */
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Person {
    private String name;

    private String lastName;

    private String phone;

    private String email;

    @Column(unique = true, nullable = false)
    private String dni;

    @Embedded
    private Address address;
}
