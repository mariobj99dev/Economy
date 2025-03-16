package org.mariobj99dev.Economy.presentation.guis.currency;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.AnvilGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import com.google.inject.Inject;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.mariobj99dev.Economy.application.dto.CurrencyDTO;
import org.mariobj99dev.Economy.application.usecases.currency.UpdateCurrencyUseCase;
import org.mariobj99dev.Economy.presentation.guis.ItemFactory;

import java.util.List;
import java.util.Optional;

public class UpdateGUI {
    private final UpdateCurrencyUseCase updateCurrencyUseCase;

    @Inject
    public UpdateGUI(UpdateCurrencyUseCase updateCurrencyUseCase) {
        this.updateCurrencyUseCase = updateCurrencyUseCase;
    }

    public void openAnvilGUI(Player player, CurrencyDTO currency, String fieldToUpdate) {
        String title = "✏ Actualizar " + fieldToUpdate;
        AnvilGui anvilGui = new AnvilGui(title);

        GuiItem instructionItem = ItemFactory.createItem(
                Material.PAPER,
                "✏ Escribe el nuevo " + fieldToUpdate,
                List.of(
                        Component.text("Ingrese el nuevo valor para:", NamedTextColor.GRAY),
                        Component.text("🔹 " + fieldToUpdate, NamedTextColor.GOLD)
                )
        );

        StaticPane pane = new StaticPane(0, 0, 1, 1);
        pane.addItem(instructionItem, 0, 0);
        anvilGui.getFirstItemComponent().addPane(pane);

        anvilGui.setOnTopClick(event -> {
            String newValue = anvilGui.getRenameText();

            if (newValue.isBlank()) {
                player.sendMessage("❌ No puedes dejar el campo vacío.");
                return;
            }

            CurrencyDTO updatedCurrency = new CurrencyDTO(
                    currency.id,
                    fieldToUpdate.equalsIgnoreCase("nombre") ? newValue : currency.name,
                    fieldToUpdate.equalsIgnoreCase("símbolo") ? newValue : currency.symbol,
                    fieldToUpdate.equalsIgnoreCase("tipo de cambio") ? Float.parseFloat(newValue) : currency.exchangeRate,
                    fieldToUpdate.equalsIgnoreCase("tasa de inflación") ? Float.parseFloat(newValue) : currency.inflationRate,
                    currency.createdAt
            );

            Optional<CurrencyDTO> result = updateCurrencyUseCase.execute(updatedCurrency);

            result.ifPresentOrElse(
                    updated -> player.sendMessage("✅ " + fieldToUpdate + " actualizado correctamente."),
                    () -> player.sendMessage("❌ No se pudo actualizar la moneda.")
            );

            player.closeInventory();
        });

        anvilGui.show(player);
    }
}
