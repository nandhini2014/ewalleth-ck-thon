package com.ewallet.order.service.order;

import com.ewallet.order.dto.OrderRequestDTO;
import com.ewallet.order.dto.OrderResponseDTO;
import com.ewallet.order.dto.ProductDTO;
import com.ewallet.order.entity.Wallet;
import com.ewallet.order.exception.InsufficientBalanceException;
import com.ewallet.order.exception.InvalidCurrencyException;
import com.ewallet.order.exception.ProductOutOfStockException;
import com.ewallet.order.service.merchant.MerchantService;
import com.ewallet.order.service.payment.PaymentService;
import com.ewallet.order.service.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private WalletService walletService;
    public OrderResponseDTO processOrder(OrderRequestDTO orderRequest) {

        OrderResponseDTO response = new OrderResponseDTO();

        try {

            // 1. Validate product availability & get product details
            ProductDTO product = merchantService.getProductById(orderRequest.getProductId());

            // 2. Validate currency + wallet + balance inside service layer
            Wallet wallet = walletService.getWalletByCustomerId(orderRequest.getWalletId());

            // 3. Calculate total price
            double totalCost = product.getPrice() * orderRequest.getRequestedQty();

            // 4. Attempt to process payment & create final order
            paymentService.processOrder(orderRequest, product, wallet, totalCost);
            // 1. Based on walletId, get the balance from Wallet Service and deduct the totalCost
            // 2. Update the product quantity in Merchant Service

            // 5. Success Response
            response.setStatus("SUCCESS");
            response.setMessage("Order processed successfully");


            return response;

        }
        // -------------------------
        // Custom Exception Handling
        // -------------------------

        catch (ProductOutOfStockException ex) {
            response.setStatus("FAILED");
            response.setMessage("Product out of stock: " + ex.getMessage());
            throw ex;
        }

        catch (InsufficientBalanceException ex) {
            response.setStatus("FAILED");
            response.setMessage("Insufficient Wallet Balance: " + ex.getMessage());
            throw ex;
        }

        catch (InvalidCurrencyException ex) {
            response.setStatus("FAILED");
            response.setMessage("Invalid Currency: " + ex.getMessage());
           throw ex;
        }

        // -------------------------
        // Generic Exception Handling
        // -------------------------
        catch (Exception ex) {
            response.setStatus("FAILED");
            response.setMessage("Unexpected Error: " + ex.getMessage());
            throw ex;
        }
    }

}
