package edu.icet.senuka.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Integer id;
    private String name;
    private Double price;
    private Double discount;
    private LocalDate expiryDate;
    private Integer quantity;
    private String category;
}
