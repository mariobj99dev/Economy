package org.mariobj99dev.Economy.domain.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Currency {
    private int id;
    private String name;
    private String symbol;
    private float exchangeRate;
    private float inflationRate;
    private LocalDateTime createdAt;

    public Currency(int id, String name, String symbol, float exchangeRate, float inflationRate, LocalDateTime createdAt) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la moneda no puede estar vacío.");
        }
        if (symbol == null || symbol.trim().isEmpty()) {
            throw new IllegalArgumentException("El símbolo de la moneda no puede estar vacío.");
        }
        if (exchangeRate <= 0) {
            throw new IllegalArgumentException("El tipo de cambio debe ser mayor que 0.");
        }
        if (inflationRate < 0) {
            throw new IllegalArgumentException("La tasa de inflación no puede ser negativa.");
        }

        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.exchangeRate = exchangeRate;
        this.inflationRate = inflationRate;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la moneda no puede estar vacío.");
        }
        this.name = name;
    }

    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) {
        if (symbol == null || symbol.trim().isEmpty()) {
            throw new IllegalArgumentException("El símbolo de la moneda no puede estar vacío.");
        }
        this.symbol = symbol;
    }

    public float getExchangeRate() { return exchangeRate; }
    public void setExchangeRate(float exchangeRate) {
        if (exchangeRate <= 0) {
            throw new IllegalArgumentException("El tipo de cambio debe ser mayor que 0.");
        }
        this.exchangeRate = exchangeRate;
    }

    public float getInflationRate() { return inflationRate; }
    public void setInflationRate(float inflationRate) {
        if (inflationRate < 0) {
            throw new IllegalArgumentException("La tasa de inflación no puede ser negativa.");
        }
        this.inflationRate = inflationRate;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return id == currency.id && name.equals(currency.name) && symbol.equals(currency.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, symbol);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", exchangeRate=" + exchangeRate +
                ", inflationRate=" + inflationRate +
                ", createdAt=" + createdAt +
                '}';
    }
}
