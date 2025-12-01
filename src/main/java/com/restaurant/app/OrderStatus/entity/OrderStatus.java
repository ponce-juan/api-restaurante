package com.restaurant.app.OrderStatus.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restaurant.app.OrderCustomer.entity.OrderCustomer;
import com.restaurant.app.OrderStatus.model.OrderStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/*
* Valores posibless para el campo status:
* 1. PENDIENTE
* 2. EN_PROCESO
* 3. FINALIZADO
* 4. CANCELADO
* 5. RECHAZADO
* 6. APROBADO
* 7. ENVIADO
* 8. RECIBIDO
* */

@Getter
@Setter
@Entity
@Table(name = "status")
@NoArgsConstructor
public class OrderStatus
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "status_name", nullable = false)
//    private String statusName;
    @Enumerated(EnumType.STRING)
    @Column(name = "status_name", nullable = false, unique = true)
    private OrderStatusEnum status;

    @OneToMany(
        mappedBy = "status",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY,
        orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private List<OrderCustomer> orders = new ArrayList<>();
}
