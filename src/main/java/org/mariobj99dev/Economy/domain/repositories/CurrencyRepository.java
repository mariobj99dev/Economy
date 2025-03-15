package org.mariobj99dev.Economy.domain.repositories;

import org.mariobj99dev.Economy.domain.models.Currency;

import java.util.List;

public interface CurrencyRepository {
    List<Currency> getAllCurrencies();
}
