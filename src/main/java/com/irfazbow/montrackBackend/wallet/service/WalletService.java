package com.irfazbow.montrackBackend.wallet.service;

import com.irfazbow.montrackBackend.wallet.dto.WalletDto;

import java.util.List;

public interface WalletService {
    public WalletDto createWallet(WalletDto walletDto);
    public WalletDto getWalletById(Long id);
    public List<WalletDto> getAllWallets();
    public WalletDto updateWallet(Long id, WalletDto walletDto);
    public void deleteWallet(Long id);
}
