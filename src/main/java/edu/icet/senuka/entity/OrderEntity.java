package edu.icet.senuka.entity;

import edu.icet.senuka.util.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private UserEntity customer;

    private String telephoneNumber;
    private Double total;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;


}
