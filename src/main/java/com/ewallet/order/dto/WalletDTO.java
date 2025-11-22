package com.ewallet.order.dto;

import lombok.Data;

@Data
public class WalletDTO {

    private String walletId;
    private String customerId;
    private Double balanceAmount;
    private String currency;
}
