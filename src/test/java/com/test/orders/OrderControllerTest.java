package com.test.orders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.orders.controller.OrderController;
import com.test.orders.dto.OrderDto;
import com.test.orders.entities.Extra;
import com.test.orders.entities.Order;
import com.test.orders.entities.VegetableEntity;
import com.test.orders.enums.Protein;
import com.test.orders.enums.Salsa;
import com.test.orders.enums.Tortilla;
import com.test.orders.enums.Vegetable;
import com.test.orders.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void testCreateOrders() throws Exception {
        Mockito.when(orderService.create(dummyOrder())).thenReturn(mockOrderResponse());
        this.mockMvc.perform(post("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJson()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private Order mockOrderResponse() {
        return Order.builder()
                .id(1L)
                .tortilla(Tortilla.CORN)
                .salsa(Salsa.MEDIUM)
                .protein(Protein.CHICKEN)
                .vegetables(Set.of(VegetableEntity.builder().vegetable(Vegetable.CABBAGE).id(1L).build(),
                        VegetableEntity.builder().vegetable(Vegetable.CORN).id(2L).build()))
                .extras(Set.of(Extra.builder().id(1L).extra("Avocado").build()))
                .build();
    }

    private OrderDto dummyOrder(){
        return OrderDto.builder()
                .tortilla(Tortilla.CORN)
                .salsa(Salsa.MEDIUM)
                .protein(Protein.CHICKEN)
                .vegetables(List.of(Vegetable.CABBAGE,Vegetable.CORN))
                .extras(List.of("Avocado"))
                .build();
    }

    private String getJson() {
        ObjectMapper mapper=new ObjectMapper();
        String data="";
        try {
            data=mapper.writeValueAsString(dummyOrder());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return data;
    }
}
