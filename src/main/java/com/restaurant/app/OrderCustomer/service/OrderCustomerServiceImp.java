//package com.restaurant.app.OrderCustomer.service;
//
//import com.restaurant.app.OrderCustomer.entity.OrderCustomer;
//import com.restaurant.app.OrderCustomer.repository.OrderCustomerRepository;
//import com.restaurant.app.OrderStatus.model.OrderStatusEnum;
//import jakarta.persistence.EntityNotFoundException;
//import jakarta.transaction.Transactional;
//import lombok.NonNull;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//public class OrderCustomerServiceImp implements OrderCustomerService
//{
//    //Dependency Injection
//    private final OrderCustomerRepository orderCustomerRepository;
//
//    public OrderCustomerServiceImp (OrderCustomerRepository orderCustomerRepository){
//        this.orderCustomerRepository = orderCustomerRepository;
//    }
//
//    @Override
//    public OrderCustomer createOrderCustomer (@NonNull OrderCustomer orderCustomer)
//    {
//        orderCustomer.setOrderDate(LocalDateTime.now());
//        return orderCustomerRepository.save(orderCustomer);
//    }
//
//    @Override
//    @Transactional
//    public OrderCustomer updateOrderCustomer (@NonNull Long id, @NonNull OrderCustomer orderCustomer)
//    {
//        OrderCustomer orderCustomerDb = orderCustomerRepository
//                                            .findById(id)
//                                            .orElse(null);
//        if(orderCustomerDb != null){
//            orderCustomerDb.setCustomer(orderCustomer.getCustomer());
//            orderCustomerDb.setOrderType(orderCustomer.getOrderType());
//            orderCustomerDb.setOrderStatus(orderCustomer.getOrderStatus());
//            orderCustomerDb.setOrderItems(orderCustomer.getOrderItems());
////            No actualizo la fecha de creacion de la orden
////            orderCustomerDb.setOrderDate(orderCustomer.getOrderDate());
//            orderCustomerDb.calculateTotalAmount();
//            return orderCustomerRepository.save(orderCustomerDb);
//        }
//        throw new EntityNotFoundException("OrderCustomer not found with id: " + orderCustomer.getId());
//    }
//
//    @Override
//    public OrderCustomer getOrderCustomerById (@NonNull Long id)
//    {
//        return orderCustomerRepository
//                   .findById(id)
//                   .orElseThrow(() -> new EntityNotFoundException("OrderCustomer not found with id: " + id));
//    }
//
//    @Override
//    public List<OrderCustomer> getAllOrderCustomers ()
//    {
//        return orderCustomerRepository.findAll();
//    }
//
//    @Override
//    public List<OrderCustomer> getOrderCustomersByStatusId (@NonNull Long orderStatusId)
//    {
//        return orderCustomerRepository
//                   .findByOrderStatus_Id(orderStatusId)
//                   .orElseThrow(() -> new EntityNotFoundException("OrderCustomers not found with status id: " + orderStatusId));
//    }
//
//    @Override
//    public List<OrderCustomer> getOrderCustomersByStatusIdAndCustomerId (@NonNull Long orderStatusId,
//                                                                       @NonNull Long customerId)
//    {
//        return orderCustomerRepository.findByOrderStatus_IdAndCustomer_Id(orderStatusId, customerId)
//                   .orElseThrow(
//                       () -> new EntityNotFoundException("OrderCustomers not found with status id: " + orderStatusId + " and customer id: " + customerId)
//                   );
//    }
//
//    @Override
//    public List<OrderCustomer> getOrderCustomersByOrderTypeId (@NonNull Long orderTypeId)
//    {
//        return orderCustomerRepository
//                   .findByOrderType_Id(orderTypeId)
//                   .orElseThrow(
//                       () -> new EntityNotFoundException("OrderCustomers not found with order type id: " + orderTypeId)
//                   );
//    }
//
//    @Override
//    public void deleteOrderCustomer (@NonNull Long id)
//    {
//        OrderCustomer orderCustomer = orderCustomerRepository
//                                          .findById(id)
//                                          .orElseThrow(
//                                              () -> new EntityNotFoundException("OrderCustomer not found with id: " + id)
//                                          );
//
//        orderCustomerRepository.delete(orderCustomer);
//    }
//}


package com.restaurant.app.OrderCustomer.service;

import com.restaurant.app.Customer.repository.CustomerRepository;
import com.restaurant.app.OrderCustomer.dto.OrderCustomerMapper;
import com.restaurant.app.OrderCustomer.dto.OrderCustomerRequest;
import com.restaurant.app.OrderCustomer.dto.OrderCustomerResponse;
import com.restaurant.app.OrderItem.dto.ItemDTO;
import com.restaurant.app.OrderCustomer.entity.OrderCustomer;
import com.restaurant.app.OrderItem.entity.OrderItem;
import com.restaurant.app.OrderItem.repository.OrderItemRepository;
import com.restaurant.app.OrderCustomer.repository.OrderCustomerRepository;
import com.restaurant.app.OrderStatus.entity.OrderStatus;
import com.restaurant.app.OrderStatus.model.OrderStatusEnum;
import com.restaurant.app.OrderStatus.repository.OrderStatusRepository;
import com.restaurant.app.OrderType.entity.OrderType;
import com.restaurant.app.OrderType.repository.OrderTypeRepository;
import com.restaurant.app.Product.entity.Product;
import com.restaurant.app.Product.repository.ProductRepository;
import com.restaurant.app.Utils.SecurityUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderCustomerServiceImp implements OrderCustomerService {

    private final OrderCustomerRepository orderCustomerRepository;
    private final OrderTypeRepository orderTypeRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    // OrderItemRepository no es estrictamente necesario si usas cascade, pero lo incluimos si necesitás operaciones directas
    private final OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public OrderCustomerResponse createOrderCustomer(OrderCustomerRequest request) {

        Long companyId = SecurityUtils.getCompanyId();

        // Buscar order type (obligatorio)
        Long orderTypeId = request.type() != null ? request.type().id() : null;
        assert orderTypeId != null;
        OrderType orderType = orderTypeRepository.findById(orderTypeId)
                .orElseThrow(() -> new EntityNotFoundException("OrderType not found with id: " + orderTypeId));

        // Buscar order status (puede venir como texto); asumimos que existe un entity OrderStatus y repo por name/type
        String statusStr = request.status();
        OrderStatus orderStatus = orderStatusRepository.findByStatus(OrderStatusEnum.toEnum(statusStr))
                .orElseThrow(() -> new EntityNotFoundException("OrderStatus not found with type: " + statusStr));

        // Cliente (opcional)
        // Si tu DTO trae client como nombre o id, adaptá. Acá asumimos String client -> no buscamos entidad Customer.
        // Si querés relacionar Customer por id, cambiar request para enviar customerId y buscar customerRepository.findById(...)
        // Por ahora lo dejamos NULL (tu entidad acepta null).
        // Customer customer = null;
        // if (request.client() != null) { ... buscar por id o crear ... }

        OrderCustomer order = new OrderCustomer();
        order.setCustomer(null); // o setear si buscás por id
        order.setType(orderType);
        order.setStatus(orderStatus);
        order.setOrderDate(LocalDateTime.now());

        // Crear OrderItems y asociarlos
        List<OrderItem> items = new ArrayList<>();
        if (request.items() != null) {
            for (ItemDTO itemDto : request.items()) {

                // Buscar producto por nombre (asumimos front envía name). Si front envía productId, usar findById.
                Product product = productRepository.findByNameIgnoreCaseAndCompanyId(itemDto.name(), companyId);
                if(product == null){
                    throw  new EntityNotFoundException("Product not found with name: " + itemDto.name());
                }

                OrderItem item = new OrderItem();
                item.setOrderCustomer(order); // importante para la relación bidireccional
                item.setProduct(product);
                item.setQuantity(itemDto.quantity());
                item.setPrice(itemDto.price());
                item.calculateSubTotal();
                items.add(item);
            }
        }

        order.setItems(items);
        order.calculateTotalAmount();

        // Guardar en cascada: OrderCustomer -> OrderItems
        return OrderCustomerMapper.toResponse(orderCustomerRepository.save(order));
    }

    @Override
    @Transactional
    public OrderCustomerResponse updateOrderCustomer(Long id, OrderCustomerRequest request) {

        Long companyId = SecurityUtils.getCompanyId();

        OrderCustomer existing = orderCustomerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("OrderCustomer not found with id: " + id));

        // Si front envía orderType, actualizar
        if (request.type() != null && request.type().id() != null) {
            OrderType orderType = orderTypeRepository.findById(request.type().id())
                    .orElseThrow(() -> new EntityNotFoundException("OrderType not found with id: " + request.type().id()));
            existing.setType(orderType);
        }

        // Si front envía status, actualizar
        if (request.status() != null) {
            OrderStatus orderStatus = orderStatusRepository.findByStatus(OrderStatusEnum.toEnum(request.status()))
                    .orElseThrow(() -> new EntityNotFoundException("OrderStatus not found with type: " + request.status()));
            existing.setStatus(orderStatus);
        }

        // Opcional: actualizar customer si enviás id (a adaptar según DTO)
        // existing.setCustomer(...);

        // Reemplazar items: limpiamos la lista actual (orphanRemoval = true hará delete)
        existing.getItems().clear();

        List<OrderItem> newItems = new ArrayList<>();
        if (request.items() != null) {
            for (ItemDTO itemDto : request.items()) {
                Product product = productRepository.findByNameIgnoreCaseAndCompanyId(itemDto.name(), companyId);
                if(product == null){
                    throw  new EntityNotFoundException("Product not found with name: " + itemDto.name());
                }

                OrderItem item = new OrderItem();
                item.setOrderCustomer(existing);
                item.setProduct(product);
                item.setQuantity(itemDto.quantity());
                item.setPrice(itemDto.price());
                item.calculateSubTotal();
                newItems.add(item);
            }
        }

        existing.setItems(newItems);

        // recalcular total
        existing.calculateTotalAmount();

        return OrderCustomerMapper.toResponse(orderCustomerRepository.save(existing));
    }

    @Override
    public OrderCustomerResponse getOrderCustomerById(Long id) {
        return orderCustomerRepository.findById(id)
                .map(OrderCustomerMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("OrderCustomer not found with id: " + id));
    }

    @Override
    public List<OrderCustomerResponse> getAllOrderCustomers() {
        return orderCustomerRepository.findAll().stream().map(OrderCustomerMapper::toResponse).toList();
    }

    @Override
    public List<OrderCustomerResponse> getOrderCustomersByStatusId(Long orderStatusId) {
        List<OrderCustomerResponse> list =
                orderCustomerRepository.findByOrderStatus_Id(orderStatusId)
                        .stream()
                        .map(OrderCustomerMapper::toResponse).toList();

        if (list.isEmpty()) {
            throw new EntityNotFoundException("OrderCustomers not found with status id: " + orderStatusId);
        }

        return list;
    }

    @Override
    public List<OrderCustomerResponse> getOrderCustomersByStatusIdAndCustomerId(Long orderStatusId, Long customerId) {
        List<OrderCustomerResponse> list = orderCustomerRepository.findByOrderStatus_IdAndCustomer_Id(orderStatusId,
                        customerId)
                .stream()
                .map(OrderCustomerMapper::toResponse).toList();
        if (list.isEmpty()) {
            throw new EntityNotFoundException("OrderCustomers not found with status id: " + orderStatusId + " and customer id: " + customerId);
        }
        return list;
    }

    @Override
    public List<OrderCustomerResponse> getOrderCustomersByOrderTypeId(Long orderTypeId) {
        List<OrderCustomerResponse> list = orderCustomerRepository.findByOrderType_Id(orderTypeId)
                .stream()
                .map(OrderCustomerMapper::toResponse).toList();

        if (list.isEmpty()) {
            throw new EntityNotFoundException("OrderCustomers not found with order type id: " + orderTypeId);
        }
        return list;
    }

    @Override
    @Transactional
    public void deleteOrderCustomer(Long id) {
        OrderCustomer orderCustomer = orderCustomerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("OrderCustomer not found with id: " + id));
        orderCustomerRepository.delete(orderCustomer);
    }
}
