package com.irfazbow.montrackBackend.wallet.controller;

import com.irfazbow.montrackBackend.wallet.dto.WalletDto;
import com.irfazbow.montrackBackend.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<WalletDto> createWallet(@RequestBody WalletDto walletDto) {
        return ResponseEntity.ok(walletService.createWallet(walletDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletDto> getWalletById(@PathVariable Long id) {
        return ResponseEntity.ok(walletService.getWalletById(id));
    }

    @GetMapping
    public ResponseEntity<List<WalletDto>> getAllWallets() {
        return ResponseEntity.ok(walletService.getAllWallets());
    }

    @PutMapping("/{id}")
    public ResponseEntity<WalletDto> updateWallet(@PathVariable Long id, @RequestBody WalletDto walletDto) {
        return ResponseEntity.ok(walletService.updateWallet(id, walletDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWallet(@PathVariable Long id) {
        walletService.deleteWallet(id);
        return ResponseEntity.noContent().build();
    }
}
