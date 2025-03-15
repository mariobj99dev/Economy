package org.mariobj99dev.Economy.presentation.guis.bank;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.mariobj99dev.Economy.application.dto.BankDTO;
import org.mariobj99dev.Economy.presentation.guis.ItemFactory;

import java.util.ArrayList;
import java.util.List;

public class BanksGUI {
    public void openGUI(Player player, List<BankDTO> banks) {
        ChestGui gui = new ChestGui(6, "Banks");
        PaginatedPane pages = new PaginatedPane(0, 0, 9, 5);

        pages.populateWithGuiItems(getPageItems(banks, player));

        gui.addPane(pages);
        gui.addPane(createBackgroundPane());
        gui.addPane(createNavigationPane(gui, pages));

        gui.show(player);
    }

    private List<GuiItem> getPageItems(List<BankDTO> banks, Player player) {
        List<GuiItem> items = new ArrayList<>();

        for (BankDTO bank : banks) {
            List<Component> lore = List.of(
                    Component.text("🏦 ID: ", NamedTextColor.YELLOW).append(Component.text(bank.id, NamedTextColor.GRAY)),
                    Component.text("📛 Name: ", NamedTextColor.YELLOW).append(Component.text(bank.name, NamedTextColor.GRAY)),
                    Component.text("👤 Owner: ", NamedTextColor.YELLOW).append(Component.text(bank.owner, NamedTextColor.GRAY)),
                    Component.text("💰 Currency ID: ", NamedTextColor.YELLOW).append(Component.text(bank.currencyId, NamedTextColor.GRAY)),
                    Component.text("💵 Reserve Ratio: ", NamedTextColor.YELLOW).append(Component.text(bank.reserveRatio + "%", NamedTextColor.GRAY)),
                    Component.text("📈 Interest Rate: ", NamedTextColor.YELLOW).append(Component.text(bank.interestRate + "%", NamedTextColor.GRAY)),
                    Component.text("⚡ Status: ", NamedTextColor.YELLOW).append(Component.text(bank.status, NamedTextColor.GREEN)),
                    Component.text("🕒 Created At: ", NamedTextColor.YELLOW).append(Component.text(bank.createdAt.toString(), NamedTextColor.GREEN))
            );

            items.add(ItemFactory.createItem(Material.GOLD_BLOCK, bank.name, lore));
        }

        return items;
    }

    private OutlinePane createBackgroundPane() {
        OutlinePane background = new OutlinePane(0, 5, 9, 1);
        background.addItem(new GuiItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE), event -> event.setCancelled(true)));
        background.setRepeat(true);
        background.setPriority(OutlinePane.Priority.LOWEST);
        return background;
    }

    private StaticPane createNavigationPane(ChestGui gui, PaginatedPane pages) {
        StaticPane navigation = new StaticPane(0, 5, 9, 1);

        navigation.addItem(new GuiItem(new ItemStack(Material.RED_WOOL), event -> {
            event.setCancelled(true);
            if (pages.getPage() > 0) {
                pages.setPage(pages.getPage() - 1);
                gui.update();
            }
        }), 0, 0);

        navigation.addItem(new GuiItem(new ItemStack(Material.GREEN_WOOL), event -> {
            event.setCancelled(true);
            if (pages.getPage() < pages.getPages() - 1) {
                pages.setPage(pages.getPage() + 1);
                gui.update();
            }
        }), 8, 0);

        navigation.addItem(new GuiItem(new ItemStack(Material.BARRIER), event -> event.getWhoClicked().closeInventory()), 4, 0);

        return navigation;
    }
}
