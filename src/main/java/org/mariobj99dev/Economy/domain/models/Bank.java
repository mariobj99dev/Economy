package org.mariobj99dev.Economy.domain.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Bank {
    private int id;
    private String name;
    private int owner;
    private long currencyId;
    private BigDecimal reserveRatio;
    private BigDecimal interestRate;
    private BigDecimal feeDeposit;
    private BigDecimal feeWithdrawal;
    private BigDecimal feeTransfer;
    private String status;
    private LocalDateTime createdAt;
    private String type;

    public Bank(int id, String name, int owner, long currencyId, BigDecimal reserveRatio,
                BigDecimal interestRate, BigDecimal feeDeposit, BigDecimal feeWithdrawal,
                BigDecimal feeTransfer, String status, LocalDateTime createdAt, String type) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.currencyId = currencyId;
        this.reserveRatio = reserveRatio;
        this.interestRate = interestRate;
        this.feeDeposit = feeDeposit;
        this.feeWithdrawal = feeWithdrawal;
        this.feeTransfer = feeTransfer;
        this.status = status;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
        this.type = type;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getOwner() { return owner; }
    public long getCurrencyId() { return currencyId; }
    public BigDecimal getReserveRatio() { return reserveRatio; }
    public BigDecimal getInterestRate() { return interestRate; }
    public BigDecimal getFeeDeposit() { return feeDeposit; }
    public BigDecimal getFeeWithdrawal() { return feeWithdrawal; }
    public BigDecimal getFeeTransfer() { return feeTransfer; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public String getType() { return type; }
}
