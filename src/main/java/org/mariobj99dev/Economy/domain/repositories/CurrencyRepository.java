package org.mariobj99dev.Economy.domain.repositories;

import org.mariobj99dev.Economy.domain.models.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyRepository {
    List<Currency> getAllCurrencies();
    Optional<Currency> getCurrencyById(int id);
    boolean createCurrency(Currency currency);
    boolean updateCurrency(Currency currency);
    boolean deleteCurrency(int id);
}
