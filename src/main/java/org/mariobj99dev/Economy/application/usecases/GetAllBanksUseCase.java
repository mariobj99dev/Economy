package org.mariobj99dev.Economy.application.usecases;

import com.google.inject.Inject;
import org.mariobj99dev.Economy.application.dto.BankDTO;
import org.mariobj99dev.Economy.domain.models.Bank;
import org.mariobj99dev.Economy.domain.repositories.BankRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllBanksUseCase {
    private final BankRepository bankRepository;

    @Inject
    public GetAllBanksUseCase(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public List<BankDTO> execute() {
        List<Bank> banks = bankRepository.getAllBanks();
        return banks.stream()
                .map(bank -> new BankDTO(
                        bank.getId(),
                        bank.getName(),
                        bank.getOwner(),
                        bank.getCurrencyId(),
                        bank.getReserveRatio(),
                        bank.getInterestRate(),
                        bank.getFeeDeposit(),
                        bank.getFeeWithdrawal(),
                        bank.getFeeTransfer(),
                        bank.getStatus(),
                        bank.getCreatedAt(),
                        bank.getType()
                ))
                .collect(Collectors.toList());
    }
}
