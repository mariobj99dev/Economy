package org.mariobj99dev.Economy.application.usecases.currency;

import com.google.inject.Inject;
import org.mariobj99dev.Economy.application.dto.CurrencyDTO;
import org.mariobj99dev.Economy.domain.models.Currency;
import org.mariobj99dev.Economy.domain.repositories.CurrencyRepository;

public class CreateCurrencyUseCase {
    private final CurrencyRepository currencyRepository;

    @Inject
    public CreateCurrencyUseCase(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public CurrencyDTO execute(String name, String symbol, float exchangeRate, float inflationRate) {
        Currency currency = new Currency(0, name, symbol, exchangeRate, inflationRate, null);
        boolean success = currencyRepository.createCurrency(currency);
        return success ? new CurrencyDTO(currency.getId(), name, symbol, exchangeRate, inflationRate, currency.getCreatedAt()) : null;
    }
}