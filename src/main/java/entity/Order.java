package entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<String> items;

    private double totalPrice;

    private LocalDateTime orderDate;

    // Геттеры и сеттеры
}
