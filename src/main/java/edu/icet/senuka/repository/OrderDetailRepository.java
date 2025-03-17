package edu.icet.senuka.repository;

import edu.icet.senuka.dto.OrderDetail;
import edu.icet.senuka.entity.OrderDetailEntity;
import edu.icet.senuka.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Integer> {
    List<OrderDetailEntity> findAllByOrder(OrderEntity order);
}
