package org.mariobj99dev.Economy.application.dto;

import java.time.LocalDateTime;

public class CurrencyDTO {
    public int id;
    public String name;
    public String symbol;
    public float exchangeRate;
    public float inflationRate;
    public LocalDateTime createdAt;

    public CurrencyDTO(int id, String name, String symbol, float exchangeRate, float inflationRate, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.exchangeRate = exchangeRate;
        this.inflationRate = inflationRate;
        this.createdAt = createdAt;
    }
}
