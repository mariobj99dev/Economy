package org.mariobj99dev.Economy.infrastructure.dependency;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.bukkit.plugin.java.JavaPlugin;
import org.mariobj99dev.Economy.application.usecases.GetAllBanksUseCase;
import org.mariobj99dev.Economy.application.usecases.currency.*;
import org.mariobj99dev.Economy.application.usecases.GetBankByIdUseCase;
import org.mariobj99dev.Economy.domain.repositories.BankRepository;
import org.mariobj99dev.Economy.domain.repositories.CurrencyRepository;
import org.mariobj99dev.Economy.infrastructure.persistence.database.DatabaseConnection;
import org.mariobj99dev.Economy.infrastructure.persistence.repositories.BankRepositoryImpl;
import org.mariobj99dev.Economy.infrastructure.persistence.repositories.CurrencyRepositoryImpl;
import org.mariobj99dev.Economy.presentation.commands.CurrencyCommand;
import org.mariobj99dev.Economy.presentation.commands.GetAllBanksCommand;
import org.mariobj99dev.Economy.presentation.commands.GetBankCommand;
import org.mariobj99dev.Economy.presentation.guis.currency.CurrenciesGUI;
import org.mariobj99dev.Economy.presentation.guis.currency.CurrencyGUI;
import org.mariobj99dev.Economy.presentation.guis.currency.DeleteGUI;
import org.mariobj99dev.Economy.presentation.guis.currency.UpdateGUI;

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

        bind(CreateCurrencyUseCase.class);
        bind(GetCurrencyByIdUseCase.class);
        bind(UpdateCurrencyUseCase.class);
        bind(DeleteCurrencyUseCase.class);
        bind(GetAllBanksUseCase.class);

        bind(GetBankByIdUseCase.class);

        bind(CurrencyCommand.class);
        bind(CurrenciesGUI.class);
        bind(CurrencyGUI.class);
        bind(DeleteGUI.class);
        bind(UpdateGUI.class);

        bind(GetAllBanksCommand.class);
        bind(GetBankCommand.class);
    }

    @Provides
    @Singleton
    public DatabaseConnection provideDatabaseConnection() {
        return new DatabaseConnection();
    }
}
