package com.ewallet.order.service.wallet;
import com.ewallet.order.entity.Wallet;
import com.ewallet.order.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet getWalletByCustomerId(Long customerId) {
        return walletRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Wallet not found for customerId: " + customerId));
    }

}
