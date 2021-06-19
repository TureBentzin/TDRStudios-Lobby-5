package de.tdrstudios.lobbyplugin.inventory.actionitem.items;


import de.tdrstudios.lobbyplugin.LobbyPlugin;
import de.tdrstudios.lobbyplugin.inventory.actionitem.ItemDefaults;
import de.tdrstudios.lobbyplugin.inventory.actionitem.ToggleCommandItem;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

public class SpeedItem extends ToggleCommandItem {
    public SpeedItem() throws InvalidConfigurationException {
        super("speed", new ItemDefaultsClass(false, 14, "§2 Speed", Material.REDSTONE), LobbyPlugin.getPlugin().getCommand("speed"));
    }

    @Override
    public void updateEnchant(Player player) {

    }
}
