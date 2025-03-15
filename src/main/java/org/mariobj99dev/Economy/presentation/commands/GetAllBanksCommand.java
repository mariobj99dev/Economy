package org.mariobj99dev.Economy.presentation.commands;

import com.google.inject.Inject;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mariobj99dev.Economy.application.usecases.GetAllBanksUseCase;
import org.mariobj99dev.Economy.presentation.guis.bank.BanksGUI;

public class GetAllBanksCommand implements CommandExecutor {
    private final GetAllBanksUseCase getAllBanksUseCase;

    @Inject
    public GetAllBanksCommand(GetAllBanksUseCase getAllBanksUseCase) {
        this.getAllBanksUseCase = getAllBanksUseCase;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Este comando solo puede ser usado por jugadores.");
            return false;
        }

        new BanksGUI().openGUI(player, getAllBanksUseCase.execute());
        return true;
    }
}
