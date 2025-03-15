package org.mariobj99dev.Economy.infrastructure.dependency;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.bukkit.plugin.java.JavaPlugin;
import org.mariobj99dev.Economy.application.usecases.GetAllBanksUseCase;
import org.mariobj99dev.Economy.application.usecases.GetAllCurrenciesUseCase;
import org.mariobj99dev.Economy.domain.repositories.BankRepository;
import org.mariobj99dev.Economy.domain.repositories.CurrencyRepository;
import org.mariobj99dev.Economy.infrastructure.persistence.database.DatabaseConnection;
import org.mariobj99dev.Economy.infrastructure.persistence.repositories.BankRepositoryImpl;
import org.mariobj99dev.Economy.infrastructure.persistence.repositories.CurrencyRepositoryImpl;
import org.mariobj99dev.Economy.presentation.commands.GetAllBanksCommand;
import org.mariobj99dev.Economy.presentation.commands.GetAllCurrenciesCommand;

public class PluginModule extends AbstractModule {
    private final JavaPlugin plugin;

    public PluginModule(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    protected void configure() {
        bind(JavaPlugin.class).toInstance(plugin);

        bind(CurrencyRepository.class).to(CurrencyRepositoryImpl.class);
        bind(BankRepository.class).to(BankRepositoryImpl.class);

        bind(GetAllCurrenciesUseCase.class);
        bind(GetAllBanksUseCase.class);
        bind(GetAllCurrenciesCommand.class);
        bind(GetAllBanksCommand.class);

    }

    @Provides
    @Singleton
    public DatabaseConnection provideDatabaseConnection() {
        return new DatabaseConnection();
    }
}
