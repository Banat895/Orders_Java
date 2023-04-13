package com.test.orders.entities;

import com.test.orders.enums.Protein;
import com.test.orders.enums.Salsa;
import com.test.orders.enums.Tortilla;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Tortilla tortilla;

    @Enumerated(EnumType.STRING)
    private Protein protein;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<VegetableEntity> vegetables;

    @Enumerated(EnumType.STRING)
    private Salsa salsa;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Extra> extras;
}
