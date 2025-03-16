package org.mariobj99dev.Economy.presentation.guis.bank;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.mariobj99dev.Economy.domain.models.Bank;
import org.mariobj99dev.Economy.presentation.guis.ItemFactory;

import java.util.List;

public class BankGUI {
    public void openGUI(Player player, Bank bank) {
        ChestGui gui = new ChestGui(5, "🏦 Información del Banco");
        StaticPane pane = new StaticPane(0, 0, 9, 5);

        pane.addItem(ItemFactory.createItem(Material.NAME_TAG, "📛 Nombre del Banco",
                List.of(Component.text(bank.getName(), NamedTextColor.GRAY))), 1, 1);

        pane.addItem(ItemFactory.createItem(Material.EMERALD, "💰 Moneda",
                List.of(Component.text("ID: " + bank.getCurrencyId(), NamedTextColor.GRAY))), 3, 1);

        pane.addItem(ItemFactory.createItem(Material.GOLD_INGOT, "💵 Ratio de Reservas",
                List.of(Component.text(bank.getReserveRatio() + "%", NamedTextColor.GRAY))), 5, 1);

        pane.addItem(ItemFactory.createItem(Material.PAPER, "📈 Tasa de Interés",
                List.of(Component.text(bank.getInterestRate() + "%", NamedTextColor.GRAY))), 7, 1);

        pane.addItem(ItemFactory.createItem(Material.PLAYER_HEAD, "👤 Propietario",
                List.of(Component.text("ID: " + bank.getOwner(), NamedTextColor.GRAY))), 1, 3);

        pane.addItem(ItemFactory.createItem(Material.REDSTONE_TORCH, "⚡ Estado",
                List.of(Component.text(bank.getStatus(), NamedTextColor.GREEN))), 3, 3);

        pane.addItem(ItemFactory.createItem(Material.CLOCK, "🕒 Creado en",
                List.of(Component.text(bank.getCreatedAt().toString(), NamedTextColor.GRAY))), 5, 3);

        pane.addItem(ItemFactory.createItem(Material.BOOK, "🔍 Más Información",
                List.of(Component.text("Haz clic para ver más detalles", NamedTextColor.GRAY))), 7, 3);

        gui.addPane(pane);
        gui.show(player);
    }
}
