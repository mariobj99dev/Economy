package org.mariobj99dev.Economy.application.usecases.currency;

import com.google.inject.Inject;
import org.mariobj99dev.Economy.domain.repositories.CurrencyRepository;

public class DeleteCurrencyUseCase {
    private final CurrencyRepository currencyRepository;

    @Inject
    public DeleteCurrencyUseCase(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public boolean execute(int id) {
        return currencyRepository.deleteCurrency(id);
    }
}