package org.mariobj99dev.Economy.infrastructure.persistence.repositories;

import com.google.inject.Inject;
import org.mariobj99dev.Economy.domain.models.Currency;
import org.mariobj99dev.Economy.domain.repositories.CurrencyRepository;
import org.mariobj99dev.Economy.infrastructure.persistence.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Currency> getCurrencyById(int id) {
        String sql = "SELECT * FROM currency WHERE id = ?";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new Currency(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("symbol"),
                        rs.getFloat("exchange_rate"),
                        rs.getFloat("inflation_rate"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la moneda", e);
        }
        return Optional.empty();
    }

    @Override
    public boolean createCurrency(Currency currency) {
        String sql = "INSERT INTO currency (name, symbol, exchange_rate, inflation_rate) VALUES (?, ?, ?, ?)";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, currency.getName());
            stmt.setString(2, currency.getSymbol());
            stmt.setFloat(3, currency.getExchangeRate());
            stmt.setFloat(4, currency.getInflationRate());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la moneda", e);
        }
    }

    @Override
    public boolean updateCurrency(Currency currency) {
        String sql = "UPDATE currency SET name = ?, symbol = ?, exchange_rate = ?, inflation_rate = ? WHERE id = ?";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, currency.getName());
            stmt.setString(2, currency.getSymbol());
            stmt.setFloat(3, currency.getExchangeRate());
            stmt.setFloat(4, currency.getInflationRate());
            stmt.setInt(5, currency.getId());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la moneda", e);
        }
    }

    @Override
    public boolean deleteCurrency(int id) {
        String sql = "DELETE FROM currency WHERE id = ?";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la moneda", e);
        }
    }
}
