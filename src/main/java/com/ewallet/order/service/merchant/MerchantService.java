package com.ewallet.order.service.merchant;

import com.ewallet.order.dto.ProductDTO;

public interface MerchantService {

    ProductDTO getProductById(Long productId);

    void updateProductQuantity(Long productId, int updatedQuantity);
}

