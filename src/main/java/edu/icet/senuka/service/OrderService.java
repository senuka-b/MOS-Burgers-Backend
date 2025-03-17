package edu.icet.senuka.service;

import edu.icet.senuka.dto.Order;
import edu.icet.senuka.dto.OrderRequest;

import java.util.List;

public interface OrderService {
    Order placeOrder(OrderRequest orderRequest);
    Order updateOrder(Order order);
    Boolean deleteOrder(Integer id);

    List<Order> getAll();
}
