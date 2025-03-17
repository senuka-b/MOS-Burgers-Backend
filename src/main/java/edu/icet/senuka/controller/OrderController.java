package edu.icet.senuka.controller;

import edu.icet.senuka.dto.Order;
import edu.icet.senuka.dto.OrderDetail;
import edu.icet.senuka.dto.OrderRequest;
import edu.icet.senuka.service.OrderDetailService;
import edu.icet.senuka.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;
    private final OrderDetailService orderDetailService;

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAll() {
        List<Order> all = service.getAll();
        if (all.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(all);
    }

    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(service.placeOrder(orderRequest));
    }

    @PutMapping("/update")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        Order updateOrder = service.updateOrder(order);
        return updateOrder != null ? ResponseEntity.ok(updateOrder)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteOrder(@RequestParam Integer id) {
        Boolean deleteOrder = service.deleteOrder(id);
        return deleteOrder ? ResponseEntity.ok(true)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/detail/get")
    public ResponseEntity<List<OrderDetail>> getByOrder(@RequestBody Order order) {
        List<OrderDetail> detailsByOrder = orderDetailService.getOrderDetailsByOrder(order);
        if (detailsByOrder.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(detailsByOrder);
    }
}
