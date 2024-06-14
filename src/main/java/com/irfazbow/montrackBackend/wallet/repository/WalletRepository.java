package com.irfazbow.montrackBackend.wallet.repository;

import com.irfazbow.montrackBackend.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
