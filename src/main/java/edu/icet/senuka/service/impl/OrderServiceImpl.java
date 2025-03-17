package edu.icet.senuka.service.impl;

import edu.icet.senuka.dto.Order;
import edu.icet.senuka.dto.OrderDetail;
import edu.icet.senuka.dto.OrderRequest;
import edu.icet.senuka.entity.OrderEntity;
import edu.icet.senuka.repository.OrderRepository;
import edu.icet.senuka.service.OrderDetailService;
import edu.icet.senuka.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ModelMapper mapper;
    private final OrderRepository repository;
    private final OrderDetailService orderDetailService;

    @Override
    @Transactional
    public Order placeOrder(OrderRequest orderRequest) {

        OrderEntity orderEntity = repository.save(mapper.map(orderRequest.getOrder(), OrderEntity.class));

        orderDetailService.saveAllOrderItems(orderRequest.getItems());

        return mapper.map(orderEntity, Order.class);
    }

    @Override
    public Order updateOrder(Order order) {
        if (!repository.existsById(order.getId())) return null;

        return mapper.map(repository.save(mapper.map(order, OrderEntity.class)), Order.class);
    }

    @Override
    public Boolean deleteOrder(Integer id) {
        if (!repository.existsById(id)) return false;

        repository.deleteById(id);

        return true;
    }

    @Override
    public List<Order> getAll() {
        return repository.findAll().
                stream()
                .map(entity -> mapper.map(entity, Order.class))
                .toList();
    }
}
