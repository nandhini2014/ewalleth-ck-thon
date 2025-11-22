package com.ewallet.order.service.wallet;

import com.ewallet.order.entity.Wallet;
import com.ewallet.order.exception.InsufficientBalanceException;
import com.ewallet.order.exception.InvalidCurrencyException;

public interface WalletService {

    Wallet getWalletByCustomerId(Long customerId);

}
