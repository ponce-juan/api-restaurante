package com.restaurant.app.common.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Embeddable
public class OrderItemId implements Serializable
{
    @Column(name="order_customer_id")
    private Long orderCustomerId;

    @Column(name="product_id")
    private Long productId;
}
