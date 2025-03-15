package org.mariobj99dev.Economy.presentation.guis;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.function.Consumer;

public class ItemFactory {
    public static GuiItem createItem(Material material, String title, List<Component> lore, Consumer<InventoryClickEvent> onClick) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.displayName(Component.text(title, NamedTextColor.GOLD).decorate(TextDecoration.BOLD));
            meta.lore(lore);
            item.setItemMeta(meta);
        }

        return new GuiItem(item, event -> {
            event.setCancelled(true); // Evita que los jugadores muevan el ítem
            if (onClick != null) {
                onClick.accept(event); // Ejecuta la acción personalizada
            }
        });
    }

    // Sobrecarga para crear ítems sin acción de clic
    public static GuiItem createItem(Material material, String title, List<Component> lore) {
        return createItem(material, title, lore, null);
    }
}
