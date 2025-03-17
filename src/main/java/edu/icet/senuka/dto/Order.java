package edu.icet.senuka.dto;

import edu.icet.senuka.util.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer id;
    private User customer;
    private String telephoneNumber;
    private Double total;
    private OrderStatus status;
}
