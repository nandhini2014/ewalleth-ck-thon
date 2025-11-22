package com.ewallet.order.repository;

import com.ewallet.order.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {
    Merchant findByMerchantId(Long merchantId);
}
