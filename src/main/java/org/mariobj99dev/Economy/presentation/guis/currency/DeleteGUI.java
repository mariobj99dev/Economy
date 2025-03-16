package org.mariobj99dev.Economy.presentation.guis.currency;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.AnvilGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import com.google.inject.Inject;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.mariobj99dev.Economy.application.dto.CurrencyDTO;
import org.mariobj99dev.Economy.application.usecases.currency.DeleteCurrencyUseCase;
import org.mariobj99dev.Economy.presentation.guis.ItemFactory;

import java.time.Duration;
import java.util.List;

public class DeleteGUI {
    private final DeleteCurrencyUseCase deleteCurrencyUseCase;
    private boolean deleted = false;
    @Inject
    public DeleteGUI(DeleteCurrencyUseCase deleteCurrencyUseCase) {
        this.deleteCurrencyUseCase = deleteCurrencyUseCase;
    }

    public void openAnvilGUI(Player player, CurrencyDTO currency) {
        // Crear la GUI de tipo Yunque
        AnvilGui anvilGui = new AnvilGui("📖 Lee Info");

        GuiItem instructionItem = ItemFactory.createItem(
                Material.PAPER,
                "📝 Escribe el nombre de la moneda",
                List.of(
                        Component.text("✏ Para confirmar la eliminación,", NamedTextColor.GRAY),
                        Component.text("   escribe ", NamedTextColor.GRAY)
                                .append(Component.text(currency.name, NamedTextColor.RED))
                                .append(Component.text(" en el yunque.")), // Nombre en rojo
                        Component.text("⚠ Esta acción no se puede deshacer.", NamedTextColor.RED)
                )
        );

        StaticPane pane = new StaticPane(0,0,1,1);
        pane.addItem(instructionItem,0,0);
        anvilGui.getFirstItemComponent().addPane(pane);

        anvilGui.setCost((short) 1);

        anvilGui.setOnNameInputChanged(newName -> {
            if (newName.equalsIgnoreCase(currency.name)) {
                deleted = true;

                Title title = Title.title(
                        Component.text("✅ Eliminado", NamedTextColor.GREEN),
                        Component.text(currency.name + " ha sido borrado.", NamedTextColor.GRAY),
                        Title.Times.times(Duration.ofMillis(500), Duration.ofSeconds(2), Duration.ofMillis(500))
                );
                player.showTitle(title);
                player.closeInventory();
            }
        });

        anvilGui.setOnTopClick(event -> {
            event.setCancelled(true);
        });

        anvilGui.setOnClose(p -> {
            if (!deleted) {
                Title title = Title.title(
                        Component.text("❌ Eliminación cancelada", NamedTextColor.RED),
                        Component.text("💰 " + currency.name + " sigue en circulación.", NamedTextColor.GRAY),
                        Title.Times.times(Duration.ofMillis(300), Duration.ofSeconds(2), Duration.ofMillis(500))
                );
                player.showTitle(title);
            }
        });

        anvilGui.show(player);
    }
}
