package org.mariobj99dev.Economy.presentation.guis.currency;

import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import com.google.inject.Inject;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.mariobj99dev.Economy.application.dto.CurrencyDTO;
import org.mariobj99dev.Economy.presentation.guis.ItemFactory;

import java.util.List;

public class CurrencyGUI {
    private final DeleteGUI deleteGUI;
    private final UpdateGUI updateGUI;

    @Inject
    public CurrencyGUI(DeleteGUI deleteGUI, UpdateGUI updateGUI) {
        this.deleteGUI = deleteGUI;
        this.updateGUI = updateGUI;
    }

    public void openGUI(Player player, CurrencyDTO currency) {
        ChestGui gui = new ChestGui(6, "💰 Información de " + currency.name);
        StaticPane pane = new StaticPane(0, 0, 9, 5);

        // Botón para editar el nombre
        pane.addItem(ItemFactory.createItem(Material.NAME_TAG, "📛 Nombre",
                List.of(Component.text(currency.name, NamedTextColor.GRAY)),
                event -> updateGUI.openAnvilGUI(player, currency, "nombre")), 1, 1);

        // Botón para editar el símbolo
        pane.addItem(ItemFactory.createItem(Material.EMERALD, "💱 Símbolo",
                List.of(Component.text(currency.symbol, NamedTextColor.GRAY)),
                event -> updateGUI.openAnvilGUI(player, currency, "símbolo")), 3, 1);

        // Botón para editar el tipo de cambio
        pane.addItem(ItemFactory.createItem(Material.GOLD_INGOT, "💵 Tipo de Cambio",
                List.of(Component.text(currency.exchangeRate + "", NamedTextColor.GRAY)),
                event -> updateGUI.openAnvilGUI(player, currency, "tipo de cambio")), 5, 1);

        // Botón para editar la tasa de inflación
        pane.addItem(ItemFactory.createItem(Material.PAPER, "📈 Tasa de Inflación",
                List.of(Component.text(currency.inflationRate + "%", NamedTextColor.GRAY)),
                event -> updateGUI.openAnvilGUI(player, currency, "tasa de inflación")), 7, 1);

        pane.addItem(ItemFactory.createItem(Material.CLOCK, "🕒 Creado en",
                List.of(Component.text(currency.createdAt.toString(), NamedTextColor.GRAY))), 4, 3);

        OutlinePane backgroundPane = new OutlinePane(0, 5, 9, 1);
        backgroundPane.addItem(ItemFactory.createItem(Material.BLACK_STAINED_GLASS_PANE, " ", List.of()));
        backgroundPane.setRepeat(true);
        backgroundPane.setPriority(OutlinePane.Priority.LOWEST);

        StaticPane navigationPane = new StaticPane(0, 5, 9, 1);

        navigationPane.addItem(ItemFactory.createItem(Material.BARRIER, "❌ Cerrar", List.of(), event -> {
            event.setCancelled(true);
            player.closeInventory();
        }), 4, 0);

        navigationPane.addItem(ItemFactory.createItem(Material.REDSTONE_BLOCK, "🗑️ Borrar Currency", List.of(), event -> {
            event.setCancelled(true);
            deleteGUI.openAnvilGUI(player, currency);
        }), 8, 0);

        gui.addPane(pane);
        gui.addPane(backgroundPane);
        gui.addPane(navigationPane);

        gui.show(player);
    }
}
