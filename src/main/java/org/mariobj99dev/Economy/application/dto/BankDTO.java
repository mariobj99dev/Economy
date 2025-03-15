package org.mariobj99dev.Economy.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BankDTO {
    public int id;
    public String name;
    public int owner;
    public long currencyId;
    public BigDecimal reserveRatio;
    public BigDecimal interestRate;
    public BigDecimal feeDeposit;
    public BigDecimal feeWithdrawal;
    public BigDecimal feeTransfer;
    public String status;
    public LocalDateTime createdAt;
    public String type;

    public BankDTO(int id, String name, int owner, long currencyId, BigDecimal reserveRatio,
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
        this.createdAt = createdAt;
        this.type = type;
    }
}
