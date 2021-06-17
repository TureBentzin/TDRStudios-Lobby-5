package de.tdrstudios.lobbyplugin.inventory.actionitem.items;

import de.tdrstudios.lobbyplugin.Chat;
import de.tdrstudios.lobbyplugin.inventory.actionitem.ActionItem;
import de.tdrstudios.lobbyplugin.inventory.actionitem.ActionParameter;
import de.tdrstudios.lobbyplugin.inventory.actionitem.ItemDefaults;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;

public class SpeedItem extends ActionItem {
    public SpeedItem() throws InvalidConfigurationException {
            super("speed", new ItemDefaultsClass(false, 11, Chat.getChatColor() + "Speed" , Material.REDSTONE));

    }

    @Override
    public void onClick(ActionParameter actionParameter) {
        
    }
}
