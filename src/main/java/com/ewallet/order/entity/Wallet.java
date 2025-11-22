package com.ewallet.order.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "wallet")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walletId;

    private Long customerId;

    private Double balanceAmount;

    private String currency;
}
