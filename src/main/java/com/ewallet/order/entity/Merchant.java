package com.ewallet.order.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "merchant")
@Data
public class Merchant {

    @Id
    private String merchantId;

    private String merchantName;

    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;

    private Long walletId;


}

