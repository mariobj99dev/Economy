package org.mariobj99dev.Economy.application.usecases;

import com.google.inject.Inject;
import org.mariobj99dev.Economy.domain.models.Bank;
import org.mariobj99dev.Economy.domain.repositories.BankRepository;

import java.util.Optional;

public class GetBankByIdUseCase {
    private final BankRepository bankRepository;

    @Inject
    public GetBankByIdUseCase(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public Optional<Bank> execute(int bankId) {
        return bankRepository.getBankById(bankId);
    }
}
