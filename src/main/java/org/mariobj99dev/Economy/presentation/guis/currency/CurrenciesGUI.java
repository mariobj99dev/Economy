package org.mariobj99dev.Economy.presentation.guis.currency;

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
import org.mariobj99dev.Economy.application.dto.CurrencyDTO;
import org.mariobj99dev.Economy.presentation.guis.ItemFactory;

import java.util.ArrayList;
import java.util.List;

public class CurrenciesGUI {
    public void openGUI(Player player, List<CurrencyDTO> currencies) {
        ChestGui gui = new ChestGui(6, "Currencies");
        PaginatedPane pages = new PaginatedPane(0, 0, 9, 5);

        pages.populateWithGuiItems(getPageItems(currencies, player));

        gui.addPane(pages);
        gui.addPane(createBackgroundPane());
        gui.addPane(createNavigationPane(gui, pages));

        gui.show(player);
    }

    private List<GuiItem> getPageItems(List<CurrencyDTO> currencies, Player player) {
        List<GuiItem> items = new ArrayList<>();

        for (CurrencyDTO CurrencyDTO : currencies) {
            List<Component> lore = List.of(
                    Component.text("🏷️ Id: ", NamedTextColor.YELLOW).append(Component.text(CurrencyDTO.id, NamedTextColor.GRAY)),
                    Component.text("🔑 Name: ", NamedTextColor.YELLOW).append(Component.text(CurrencyDTO.name, NamedTextColor.GRAY)),
                    Component.text("⚙️ Symbol: ", NamedTextColor.YELLOW).append(Component.text(CurrencyDTO.symbol, NamedTextColor.GRAY)),
                    Component.text("🙍🏻 Exchange Rate: ", NamedTextColor.YELLOW).append(Component.text(CurrencyDTO.exchangeRate, NamedTextColor.GRAY)),
                    Component.text("🏦 Inflation Rate: ", NamedTextColor.YELLOW).append(Component.text(CurrencyDTO.inflationRate, NamedTextColor.GRAY)),
                    Component.text("💰 Created At: ", NamedTextColor.YELLOW).append(Component.text(String.valueOf(CurrencyDTO.createdAt), NamedTextColor.GREEN))
            );

            items.add(ItemFactory.createItem(Material.SUNFLOWER, CurrencyDTO.name, lore));
        }

        return items;
    }

    private OutlinePane createBackgroundPane() {
        OutlinePane background = new OutlinePane(0, 5, 9, 1);
        background.addItem(new GuiItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE), event -> {
            event.setCancelled(true);
        }));

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
