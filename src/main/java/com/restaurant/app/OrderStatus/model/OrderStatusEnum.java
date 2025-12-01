package com.restaurant.app.OrderStatus.model;

import java.util.Arrays;

public enum OrderStatusEnum
{
    PENDING,
    IN_PROGRESS,
    COMPLETED,
    CANCELED;

    public static boolean exists(String value) {
        if (value == null) return false;
        try {
            OrderStatusEnum.valueOf(value.toUpperCase());
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    public static OrderStatusEnum toEnum(String value) {
        return Arrays.stream(OrderStatusEnum.values())
                .filter(v -> v.name().equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}
