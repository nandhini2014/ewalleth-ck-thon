package com.ewallet.order.service.merchant;

import com.ewallet.order.dto.ProductDTO;
import com.ewallet.order.entity.Product;
import com.ewallet.order.exception.ProductOutOfStockException;
import com.ewallet.order.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDTO getProductById(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductOutOfStockException("Product not found"));

        if (product.getQuantity() <= 0) {
            throw new ProductOutOfStockException(
                    "Product '" + product.getProductName() + "' is out of stock"
            );
        }

        ProductDTO dto = new ProductDTO();
        BeanUtils.copyProperties(product, dto);

        return dto;
    }

    @Override
    public void updateProductQuantity(Long productId, int updatedQuantity) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductOutOfStockException("Product not found"));

        if (updatedQuantity < 0) {
            throw new ProductOutOfStockException(
                    "Cannot set product quantity below 0"
            );
        }

        product.setQuantity(updatedQuantity);
        productRepository.save(product);
    }
}
