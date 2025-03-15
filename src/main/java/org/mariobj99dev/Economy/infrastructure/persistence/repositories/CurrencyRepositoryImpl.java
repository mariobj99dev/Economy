package org.mariobj99dev.Economy.infrastructure.persistence.repositories;

import com.google.inject.Inject;
import org.mariobj99dev.Economy.domain.models.Currency;
import org.mariobj99dev.Economy.domain.repositories.CurrencyRepository;
import org.mariobj99dev.Economy.infrastructure.persistence.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CurrencyRepositoryImpl implements CurrencyRepository {
    private final DatabaseConnection databaseConnection;

    @Inject
    public CurrencyRepositoryImpl(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public List<Currency> getAllCurrencies() {
        List<Currency> currencies = new ArrayList<>();
        String sql = "SELECT * FROM currency";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                currencies.add(new Currency(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("symbol"),
                        rs.getFloat("exchange_rate"),
                        rs.getFloat("inflation_rate"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las monedas", e);
        }
        return currencies;
    }
}
