package org.mariobj99dev.Economy.presentation.commands;

import com.google.inject.Inject;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mariobj99dev.Economy.application.dto.CurrencyDTO;
import org.mariobj99dev.Economy.application.usecases.currency.CreateCurrencyUseCase;
import org.mariobj99dev.Economy.application.usecases.currency.GetCurrencyByIdUseCase;
import org.mariobj99dev.Economy.application.usecases.currency.GetAllCurrenciesUseCase;
import org.mariobj99dev.Economy.application.usecases.currency.UpdateCurrencyUseCase;
import org.mariobj99dev.Economy.application.usecases.currency.DeleteCurrencyUseCase;
import org.mariobj99dev.Economy.presentation.guis.currency.CurrenciesGUI;
import org.mariobj99dev.Economy.presentation.guis.currency.CurrencyGUI;

import java.util.List;
import java.util.Optional;

public class CurrencyCommand implements CommandExecutor {
    private final CreateCurrencyUseCase createCurrencyUseCase;
    private final GetCurrencyByIdUseCase getCurrencyByIdUseCase;
    private final GetAllCurrenciesUseCase getAllCurrenciesUseCase;
    private final UpdateCurrencyUseCase updateCurrencyUseCase;
    private final DeleteCurrencyUseCase deleteCurrencyUseCase;
    private final CurrenciesGUI currenciesGUI;
    private final CurrencyGUI currencyGUI;

    @Inject
    public CurrencyCommand(CreateCurrencyUseCase createCurrencyUseCase,
                           GetCurrencyByIdUseCase getCurrencyByIdUseCase,
                           GetAllCurrenciesUseCase getAllCurrenciesUseCase,
                           UpdateCurrencyUseCase updateCurrencyUseCase,
                           DeleteCurrencyUseCase deleteCurrencyUseCase, CurrenciesGUI currenciesGUI, CurrencyGUI currencyGUI) {
        this.createCurrencyUseCase = createCurrencyUseCase;
        this.getCurrencyByIdUseCase = getCurrencyByIdUseCase;
        this.getAllCurrenciesUseCase = getAllCurrenciesUseCase;
        this.updateCurrencyUseCase = updateCurrencyUseCase;
        this.deleteCurrencyUseCase = deleteCurrencyUseCase;
        this.currenciesGUI = currenciesGUI;
        this.currencyGUI = currencyGUI;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage("§eUso: /currency <create|get|update|delete|list> [params]");
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "create":
                if (args.length < 5) {
                    sender.sendMessage("§eUso: /currency create <name> <symbol> <exchange_rate> <inflation_rate>");
                    return false;
                }

                CurrencyDTO createdCurrency = createCurrencyUseCase.execute(args[1], args[2], Float.parseFloat(args[3]), Float.parseFloat(args[4]));

                if (createdCurrency != null) {
                    sender.sendMessage("§a✅ Moneda creada exitosamente: §e" + createdCurrency.name +
                            " (" + createdCurrency.symbol + ")" +
                            "\n§6🔹 Tipo de cambio: §e" + createdCurrency.exchangeRate +
                            "\n§6🔹 Inflación: §e" + createdCurrency.inflationRate + "%");
                } else {
                    sender.sendMessage("§c❌ Error al crear la moneda.");
                }
                break;

            case "get":
                if (args.length < 2) {
                    sender.sendMessage("§eUso: /currency get <id>");
                    return false;
                }

                Optional<CurrencyDTO> currency = getCurrencyByIdUseCase.execute(Integer.parseInt(args[1]));

                if (currency.isPresent()) {
                    if (sender instanceof Player player) {
                        currencyGUI.openGUI(player, currency.get());
                    } else {
                        sender.sendMessage("§6🔹 Moneda: §e" + currency.get().name +
                                "\n§6🔹 Símbolo: §e" + currency.get().symbol +
                                "\n§6🔹 Tipo de cambio: §e" + currency.get().exchangeRate +
                                "\n§6🔹 Inflación: §e" + currency.get().inflationRate + "%");
                    }
                } else {
                    sender.sendMessage("§c❌ No se encontró la moneda.");
                }
                break;

            case "list":
                List<CurrencyDTO> currencies = getAllCurrenciesUseCase.execute();
                if (sender instanceof Player player) {
                    currenciesGUI.openGUI(player, currencies);
                } else {
                    sender.sendMessage("§e📜 Lista de monedas:");
                    for (CurrencyDTO currencyDTO : currencies) {
                        sender.sendMessage(" - §6" + currencyDTO.name + "§e (" + currencyDTO.symbol + ")");
                    }
                }
                break;

            case "update":
                if (args.length < 2) {
                    sender.sendMessage("§eUso: /currency update <id>");
                    return false;
                }

                Optional<CurrencyDTO> currencyOpt = getCurrencyByIdUseCase.execute(Integer.parseInt(args[1]));

                if (currencyOpt.isEmpty()) {
                    sender.sendMessage("§c❌ No se encontró la moneda con ID: " + args[1]);
                    return false;
                }

                if (sender instanceof Player player) {
                    currencyGUI.openGUI(player, currencyOpt.get()); // Abre la GUI para actualizar
                } else {
                    sender.sendMessage("§eEste comando solo está disponible para jugadores.");
                }
                break;

            case "delete":
                if (args.length < 2) {
                    sender.sendMessage("§eUso: /currency delete <id>");
                    return false;
                }
                boolean deleted = deleteCurrencyUseCase.execute(Integer.parseInt(args[1]));
                sender.sendMessage(deleted ? "§a✅ Moneda eliminada" : "§c❌ Error al eliminar moneda");
                break;

            default:
                sender.sendMessage("§c❌ Comando no reconocido.");
        }
        return true;
    }
}
