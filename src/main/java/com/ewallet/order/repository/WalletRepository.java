package com.ewallet.order.repository;

import com.ewallet.order.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findByCustomerId(Long customerId);

}
