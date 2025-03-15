package org.mariobj99dev.Economy.presentation.commands;

import com.google.inject.Inject;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mariobj99dev.Economy.application.usecases.GetBankByIdUseCase;
import org.mariobj99dev.Economy.presentation.guis.bank.BankGUI;

public class GetBankCommand implements CommandExecutor {
    private final GetBankByIdUseCase getBankByIdUseCase;

    @Inject
    public GetBankCommand(GetBankByIdUseCase getBankByIdUseCase) {
        this.getBankByIdUseCase = getBankByIdUseCase;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Este comando solo puede ser usado por jugadores.");
            return false;
        }

        if (args.length != 1) {
            player.sendMessage("Uso: /getbank <id>");
            return false;
        }

        try {
            int bankId = Integer.parseInt(args[0]);
            getBankByIdUseCase.execute(bankId).ifPresentOrElse(
                    bank -> new BankGUI().openGUI(player, bank),
                    () -> player.sendMessage("❌ No se encontró el banco con ID " + bankId)
            );
        } catch (NumberFormatException e) {
            player.sendMessage("❌ El ID debe ser un número válido.");
        }

        return true;
    }
}
