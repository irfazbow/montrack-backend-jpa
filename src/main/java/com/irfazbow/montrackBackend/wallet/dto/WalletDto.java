package com.irfazbow.montrackBackend.wallet.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class WalletDto {
    private Long id;
    private String walletName;
    private BigDecimal target;
    private BigDecimal reached;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean isActive;
    private Long userId;
    private Long currencyId;
    private String activeCurrency;
}
