package org.mariobj99dev.Economy.application.usecases.currency;

import com.google.inject.Inject;
import org.mariobj99dev.Economy.application.dto.CurrencyDTO;
import org.mariobj99dev.Economy.domain.models.Currency;
import org.mariobj99dev.Economy.domain.repositories.CurrencyRepository;

import java.util.Optional;

public class GetCurrencyByIdUseCase {
    private final CurrencyRepository currencyRepository;

    @Inject
    public GetCurrencyByIdUseCase(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public Optional<CurrencyDTO> execute(int id) {
        return currencyRepository.getCurrencyById(id)
                .map(currency -> new CurrencyDTO(
                        currency.getId(),
                        currency.getName(),
                        currency.getSymbol(),
                        currency.getExchangeRate(),
                        currency.getInflationRate(),
                        currency.getCreatedAt()
                ));
    }
}
