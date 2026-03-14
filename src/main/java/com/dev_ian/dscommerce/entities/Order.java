package com.dev_ian.dscommerce.entities;

import com.dev_ian.dscommerce.enums.OrderStatus;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "tb_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant moment;
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;
}
