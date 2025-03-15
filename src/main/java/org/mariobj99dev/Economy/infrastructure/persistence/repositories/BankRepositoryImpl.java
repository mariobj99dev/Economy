package org.mariobj99dev.Economy.infrastructure.persistence.repositories;

import com.google.inject.Inject;
import org.mariobj99dev.Economy.domain.models.Bank;
import org.mariobj99dev.Economy.domain.repositories.BankRepository;
import org.mariobj99dev.Economy.infrastructure.persistence.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BankRepositoryImpl implements BankRepository {
    private final DatabaseConnection databaseConnection;

    @Inject
    public BankRepositoryImpl(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public List<Bank> getAllBanks() {
        List<Bank> banks = new ArrayList<>();
        String sql = "SELECT * FROM bank";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                banks.add(new Bank(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("owner"),
                        rs.getLong("currency_id"),
                        rs.getBigDecimal("reserve_ratio"),
                        rs.getBigDecimal("interest_rate"),
                        rs.getBigDecimal("fee_deposit"),
                        rs.getBigDecimal("fee_withdrawal"),
                        rs.getBigDecimal("fee_transfer"),
                        rs.getString("status"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getString("type")
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los bancos", e);
        }
        return banks;
    }

    @Override
    public Optional<Bank> getBankById(int id) {
        String sql = "SELECT * FROM bank WHERE id = ?";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new Bank(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("owner"),
                        rs.getLong("currency_id"),
                        rs.getBigDecimal("reserve_ratio"),
                        rs.getBigDecimal("interest_rate"),
                        rs.getBigDecimal("fee_deposit"),
                        rs.getBigDecimal("fee_withdrawal"),
                        rs.getBigDecimal("fee_transfer"),
                        rs.getString("status"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getString("type")
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el banco", e);
        }
        return Optional.empty();
    }
}
