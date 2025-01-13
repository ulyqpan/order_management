package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    public Order createOrder(CreateOrderDto createOrderDto) {
        if (createOrderDto.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one item");
        }
        if (createOrderDto.getTotalPrice() <= 0) {
            throw new IllegalArgumentException("Total price must be greater than zero");
        }

        Order order = new Order();
        order.setItems(createOrderDto.getItems());
        order.setTotalPrice(createOrderDto.getTotalPrice());
        order.setOrderDate(LocalDateTime.now());

        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, UpdateOrderDto updateOrderDto) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        existingOrder.setItems(updateOrderDto.getItems());
        existingOrder.setTotalPrice(updateOrderDto.getTotalPrice());

        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        orderRepository.delete(order);
    }

    public List<Order> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findByOrderDateBetween(startDate, endDate);
    }
}
