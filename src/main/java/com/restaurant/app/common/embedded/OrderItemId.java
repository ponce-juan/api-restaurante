package com.restaurant.app.common.embedded;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Embeddable
public class OrderItemId implements Serializable
{
    private Long orderCustomerId;
    private Long productId;
}
