package com.vikrant.mediaocean.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "BILL")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String billId;

    @NotNull
    private double totalAmount;
}
