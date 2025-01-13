package dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class UpdateOrderDto {

    @NotNull(message = "Items cannot be null")
    @Size(min = 1, message = "Order must have at least one item")
    private List<String> items;

    @Min(value = 1, message = "Total price must be greater than zero")
    private double totalPrice;

    // Геттеры и сеттеры
}
