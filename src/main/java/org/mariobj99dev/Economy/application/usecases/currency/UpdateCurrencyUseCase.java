package org.mariobj99dev.Economy.application.usecases.currency;

import com.google.inject.Inject;
import org.mariobj99dev.Economy.application.dto.CurrencyDTO;
import org.mariobj99dev.Economy.domain.models.Currency;
import org.mariobj99dev.Economy.domain.repositories.CurrencyRepository;

import java.util.Optional;

public class UpdateCurrencyUseCase {
    private final CurrencyRepository currencyRepository;

    @Inject
    public UpdateCurrencyUseCase(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public Optional<CurrencyDTO> execute(CurrencyDTO currencyDTO) {
        Currency currency = new Currency(
                currencyDTO.id,
                currencyDTO.name,
                currencyDTO.symbol,
                currencyDTO.exchangeRate,
                currencyDTO.inflationRate,
                currencyDTO.createdAt
        );

        boolean updated = currencyRepository.updateCurrency(currency);
        if (!updated) return Optional.empty();

        return currencyRepository.getCurrencyById(currency.getId()).map(updatedCurrency ->
                new CurrencyDTO(
                        updatedCurrency.getId(),
                        updatedCurrency.getName(),
                        updatedCurrency.getSymbol(),
                        updatedCurrency.getExchangeRate(),
                        updatedCurrency.getInflationRate(),
                        updatedCurrency.getCreatedAt()
                )
        );
    }
}
