package org.mariobj99dev.Economy;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.bukkit.plugin.java.JavaPlugin;
import org.mariobj99dev.Economy.infrastructure.dependency.PluginModule;
import org.mariobj99dev.Economy.infrastructure.persistence.database.DatabaseConnection;
import org.mariobj99dev.Economy.presentation.commands.CurrencyCommand;
import org.mariobj99dev.Economy.presentation.commands.GetAllBanksCommand;
import org.mariobj99dev.Economy.presentation.commands.GetBankCommand;

public final class Main extends JavaPlugin {

    private Injector injector;

    @Override
    public void onEnable() {
        getLogger().info("Economy Plugin activado!");

        injector = Guice.createInjector(new PluginModule(this));

        GetAllBanksCommand getAllBanksCommand = injector.getInstance(GetAllBanksCommand.class);
        GetBankCommand getBankCommand = injector.getInstance(GetBankCommand.class);
        CurrencyCommand currencyCommand = injector.getInstance(CurrencyCommand.class);

        getCommand("getbanks").setExecutor(getAllBanksCommand);
        getCommand("getbank").setExecutor(getBankCommand);
        getCommand("currency").setExecutor(currencyCommand);

    }

    @Override
    public void onDisable() {
        getLogger().info("Economy Plugin desactivado!");

        // Cerrar la conexión a la base de datos
        DatabaseConnection databaseConnection = injector.getInstance(DatabaseConnection.class);
        databaseConnection.closePool();
    }
}
