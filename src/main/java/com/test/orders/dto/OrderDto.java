package com.test.orders.dto;

import com.test.orders.enums.Protein;
import com.test.orders.enums.Salsa;
import com.test.orders.enums.Tortilla;
import com.test.orders.enums.Vegetable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    @NotNull(message = "Tortilla cant be empty")
    private Tortilla tortilla;

    @NotNull(message = "Protein cant be empty")
    private Protein protein;

    @NotNull(message = "Salsa cant be empty")
    private Salsa salsa;

    @NotNull(message = "Vegetables cant be empty")
    private List<Vegetable> vegetables;

    private List<String> extras;

}
