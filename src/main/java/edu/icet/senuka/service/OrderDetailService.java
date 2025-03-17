package edu.icet.senuka.service;

import edu.icet.senuka.dto.Order;
import edu.icet.senuka.dto.OrderDetail;
import edu.icet.senuka.dto.OrderItemRequest;

import java.util.List;

public interface OrderDetailService {
    OrderDetail saveOrderDetail(OrderDetail orderDetail);
    List<OrderDetail> saveAllOrderItems(List<OrderItemRequest> orderItemRequests);
    List<OrderDetail> getAll();
    List<OrderDetail> getOrderDetailsByOrder(Order order);
}
