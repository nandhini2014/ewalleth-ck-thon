package com.ewallet.order.service.payment;
import com.ewallet.order.dto.OrderRequestDTO;
import com.ewallet.order.dto.ProductDTO;
import com.ewallet.order.entity.Merchant;
import com.ewallet.order.entity.Product;
import com.ewallet.order.entity.Wallet;
import com.ewallet.order.repository.MerchantRepository;
import com.ewallet.order.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final WalletRepository walletRepository;
    private final MerchantRepository merchantRepository;

    @Override
    @Transactional
    public void processOrder(OrderRequestDTO orderRequest, ProductDTO product, Wallet customerWallet, Double totalCost) {

        // 1. Validate customer wallet balance
        if (customerWallet.getBalanceAmount() < totalCost) {
            throw new RuntimeException("Insufficient balance in customer wallet!");
        }

        // 2. Deduct from customer wallet
        double updatedCustomerBalance = customerWallet.getBalanceAmount() - totalCost;
        customerWallet.setBalanceAmount(updatedCustomerBalance);
        walletRepository.save(customerWallet);

        // 3. Fetch merchant wallet from merchantId
        Merchant merchant = merchantRepository.findById(orderRequest.getMerchantId())
                .orElseThrow(() -> new RuntimeException("Merchant not found"));

        Long merchantWalletId = merchant.getWalletId();

        Wallet merchantWallet = walletRepository.findById(merchantWalletId)
                .orElseThrow(() -> new RuntimeException("Merchant wallet not found"));

        // 4. Add amount to merchant wallet
        double updatedMerchantBalance = merchantWallet.getBalanceAmount() + totalCost;
        merchantWallet.setBalanceAmount(updatedMerchantBalance);
        walletRepository.save(merchantWallet);

        // 5. (Optional) Save Payment Transaction if you have a table
    }
}
