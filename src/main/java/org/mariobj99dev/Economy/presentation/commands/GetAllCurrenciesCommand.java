package org.mariobj99dev.Economy.presentation.commands;

import com.google.inject.Inject;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mariobj99dev.Economy.application.usecases.GetAllCurrenciesUseCase;
import org.mariobj99dev.Economy.presentation.guis.currency.CurrenciesGUI;

public class GetAllCurrenciesCommand implements CommandExecutor {
    private final GetAllCurrenciesUseCase getAllCurrenciesUseCase;

    @Inject
    public GetAllCurrenciesCommand(GetAllCurrenciesUseCase getAllCurrenciesUseCase) {
        this.getAllCurrenciesUseCase = getAllCurrenciesUseCase;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Este comando solo puede ser usado por jugadores.");
            return false;
        }

        new CurrenciesGUI().openGUI(player, getAllCurrenciesUseCase.execute());
        return true;
    }
}
