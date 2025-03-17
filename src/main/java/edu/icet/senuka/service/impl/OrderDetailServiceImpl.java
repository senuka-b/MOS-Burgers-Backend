package edu.icet.senuka.service.impl;

import edu.icet.senuka.dto.Order;
import edu.icet.senuka.dto.OrderDetail;
import edu.icet.senuka.dto.OrderItemRequest;
import edu.icet.senuka.entity.ItemEntity;
import edu.icet.senuka.entity.OrderDetailEntity;
import edu.icet.senuka.entity.OrderEntity;
import edu.icet.senuka.repository.OrderDetailRepository;
import edu.icet.senuka.repository.OrderRepository;
import edu.icet.senuka.service.OrderDetailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    private final ModelMapper mapper;
    private final OrderDetailRepository repository;

    @Override
    public OrderDetail saveOrderDetail(OrderDetail orderDetail) {

        return mapper.map(repository.save(mapper.map(orderDetail, OrderDetailEntity.class)), OrderDetail.class);
    }

    @Override
    @Transactional
    public List<OrderDetail> saveAllOrderItems(List<OrderItemRequest> orderItemRequests) {
        List<OrderDetailEntity> orderDetailEntities = orderItemRequests.stream().map(
                orderItemRequest ->
                        OrderDetailEntity.builder()
                                .item(ItemEntity.builder().id(orderItemRequest.getItemID()).build())
                                .quantity(orderItemRequest.getQuantity())
                                .build()

        ).toList();

        return repository.saveAll(orderDetailEntities)
                .stream()
                .map(orderDetailEntity -> mapper.map(orderDetailEntity, OrderDetail.class))
                .toList();


    }

    @Override
    public List<OrderDetail> getAll() {
        return repository.findAll()
                .stream()
                .map(orderDetailEntity -> mapper.map(orderDetailEntity, OrderDetail.class))
                .toList();
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrder(Order order) {
        List<OrderDetailEntity> allByOrder = repository.findAllByOrder(mapper.map(order, OrderEntity.class));

        return allByOrder.stream()
                .map(orderDetailEntity -> mapper.map(orderDetailEntity, OrderDetail.class))
                .toList();
    }
}
