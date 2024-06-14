package com.irfazbow.montrackBackend.wallet.service.impl;

import com.irfazbow.montrackBackend.wallet.dto.WalletDto;
import com.irfazbow.montrackBackend.wallet.entity.Wallet;
import com.irfazbow.montrackBackend.wallet.repository.WalletRepository;
import com.irfazbow.montrackBackend.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public WalletDto createWallet(WalletDto walletDto) {
        Wallet wallet = new Wallet();
        wallet.setWalletName(walletDto.getWalletName());
        wallet.setTarget(walletDto.getTarget());
        wallet.setReached(walletDto.getReached());
        wallet.setCreatedAt(Instant.now());
        wallet.setUpdatedAt(Instant.now());
        wallet.setIsActive(walletDto.getIsActive());
        wallet.setActiveCurrency(walletDto.getActiveCurrency());

        // You need to set the user and currency entities appropriately
        // wallet.setUser(user);
        // wallet.setCurrency(currency);

        walletRepository.save(wallet);

        walletDto.setId(wallet.getId());
        walletDto.setCreatedAt(wallet.getCreatedAt());
        walletDto.setUpdatedAt(wallet.getUpdatedAt());
        return walletDto;
    }

    @Override
    public WalletDto getWalletById(Long id) {
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new RuntimeException("Wallet not found"));
        return convertToDTO(wallet);
    }

    @Override
    public List<WalletDto> getAllWallets() {
        return walletRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public WalletDto updateWallet(Long id, WalletDto walletDto) {
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new RuntimeException("Wallet not found"));

        wallet.setWalletName(walletDto.getWalletName());
        wallet.setTarget(walletDto.getTarget());
        wallet.setReached(walletDto.getReached());
        wallet.setUpdatedAt(Instant.now());
        wallet.setIsActive(walletDto.getIsActive());
        wallet.setActiveCurrency(walletDto.getActiveCurrency());

        walletRepository.save(wallet);

        return convertToDTO(wallet);
    }

    @Override
    public void deleteWallet(Long id) {
        walletRepository.deleteById(id);
    }

    private WalletDto convertToDTO(Wallet wallet) {
        WalletDto walletDto = new WalletDto();
        walletDto.setId(wallet.getId());
        walletDto.setWalletName(wallet.getWalletName());
        walletDto.setTarget(wallet.getTarget());
        walletDto.setReached(wallet.getReached());
        walletDto.setCreatedAt(wallet.getCreatedAt());
        walletDto.setUpdatedAt(wallet.getUpdatedAt());
        walletDto.setIsActive(wallet.getIsActive());
//        walletDto.setUserId(wallet.getUser().getId());
//        walletDto.setCurrencyId(wallet.getCurrency().getId());
        walletDto.setActiveCurrency(wallet.getActiveCurrency());
        return walletDto;
    }
}
