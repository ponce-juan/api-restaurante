package com.restaurant.app.OrderCustomer.repository;

import com.restaurant.app.OrderCustomer.entity.OrderCustomer;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderCustomerRepository extends JpaRepository<OrderCustomer, Long>
{
    Optional<List<OrderCustomer>> findByOrderStatus_Id (@NonNull Long orderStatusId);
    Optional<List<OrderCustomer>> findByOrderStatus_IdAndCustomer_Id (@NonNull Long orderStatusId,
                                                                  @NonNull Long customerId);
    Optional<List<OrderCustomer>> findByOrderType_Id (@NonNull Long orderTypeId);
}
