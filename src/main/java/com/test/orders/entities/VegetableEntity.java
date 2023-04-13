package com.test.orders.entities;

import com.test.orders.enums.Vegetable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "vegetables")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VegetableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Vegetable vegetable;
}
