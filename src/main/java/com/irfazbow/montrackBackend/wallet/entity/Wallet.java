package com.irfazbow.montrackBackend.wallet.entity;

import com.irfazbow.montrackBackend.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wallet_id_gen")
    @SequenceGenerator(name = "wallet_id_gen", sequenceName = "wallet_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "wallet_name", length = 30)
    private String walletName;

    @Column(name = "target", precision = 10, scale = 2)
    private BigDecimal target;

    @Column(name = "reached", precision = 10, scale = 2)
    private BigDecimal reached;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @Column(name = "is_active")
    private Boolean isActive;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

//    @ManyToOne
//    @JoinColumn(name = "currency_id")
//    private Currency currency;

    @Column(name = "active_currency", length = 5)
    private String activeCurrency;
}
