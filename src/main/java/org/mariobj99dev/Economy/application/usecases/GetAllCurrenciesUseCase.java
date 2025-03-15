package org.mariobj99dev.Economy.application.usecases;


import com.google.inject.Inject;
import org.mariobj99dev.Economy.application.dto.CurrencyDTO;
import org.mariobj99dev.Economy.domain.models.Currency;
import org.mariobj99dev.Economy.domain.repositories.CurrencyRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllCurrenciesUseCase {
    private final CurrencyRepository currencyRepository;

    @Inject
    public GetAllCurrenciesUseCase(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<CurrencyDTO> execute() {
        List<Currency> currencies = currencyRepository.getAllCurrencies();
        return currencies.stream()
                .map(currency -> new CurrencyDTO(
                        currency.getId(),
                        currency.getName(),
                        currency.getSymbol(),
                        currency.getExchangeRate(),
                        currency.getInflationRate(),
                        currency.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }
}
