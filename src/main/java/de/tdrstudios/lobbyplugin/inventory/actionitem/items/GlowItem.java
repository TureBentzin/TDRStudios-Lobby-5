package de.tdrstudios.lobbyplugin.inventory.actionitem.items;

import de.tdrstudios.lobbyplugin.LobbyPlugin;
import de.tdrstudios.lobbyplugin.commands.GlowCommand;
import de.tdrstudios.lobbyplugin.inventory.actionitem.ActionItemListener;
import de.tdrstudios.lobbyplugin.inventory.actionitem.ItemDefaults;
import de.tdrstudios.lobbyplugin.inventory.actionitem.ToggleCommandItem;
import de.tdrstudios.lobbyplugin.utils.inventory.ActionItemContent;
import de.tdrstudios.lobbyplugin.utils.inventory.InventoryUtils;
import javafx.scene.effect.Glow;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

public class GlowItem extends ToggleCommandItem {


    public GlowItem() throws InvalidConfigurationException {
        super("glow", new ItemDefaultsClass(false, 15, "§3Glow", Material.GHAST_TEAR), LobbyPlugin.getPlugin().getCommand("glow"));

        ActionItemListener.getInstance().registerActionItem(this);
        InventoryUtils.registerInventoryContent(new ActionItemContent(this, GlowCommand.getInstance().getPermission()))
        ;
    }


    @Override
    public void updateEnchant( Player player) {
        setActive(GlowCommand.getInstance().isGlowing(player));
    }
}
