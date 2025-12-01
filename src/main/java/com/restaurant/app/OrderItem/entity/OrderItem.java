package com.restaurant.app.OrderItem.entity;

import com.restaurant.app.OrderCustomer.entity.OrderCustomer;
import com.restaurant.app.Product.entity.Product;
import com.restaurant.app.common.embedded.OrderItemId;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "order_items")
public class OrderItem
{
//    @EmbeddedId
//    private OrderItemId id;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

//Many OrderItem can have one OrderCustomer
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "order_customer_id", nullable = false)
    @ToString.Exclude
    private OrderCustomer orderCustomer;

//Many OrderItem can have one Product
    @ManyToOne(fetch=FetchType.LAZY)
//    @MapsId("productId") //Field name in OrderItemId
    @JoinColumn(name = "product_id", nullable = false)
    @ToString.Exclude
    private Product product;

    private int quantity;

    private BigDecimal price;

    private BigDecimal subTotal;

    public void calculateSubTotal(){
        subTotal = BigDecimal.ZERO;
        this.subTotal = price.multiply(BigDecimal.valueOf(quantity));
    }
}
