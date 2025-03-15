package org.mariobj99dev.Economy.domain.repositories;

import org.mariobj99dev.Economy.domain.models.Bank;

import java.util.List;
import java.util.Optional;

public interface BankRepository {
    List<Bank> getAllBanks();
    Optional<Bank> getBankById(int id);
}