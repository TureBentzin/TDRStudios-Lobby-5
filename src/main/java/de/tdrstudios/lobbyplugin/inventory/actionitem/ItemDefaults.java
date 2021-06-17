package de.tdrstudios.lobbyplugin.inventory.actionitem;

import org.bukkit.Material;

public interface ItemDefaults {
    boolean isActive();

    int getSlot();

    Material getMaterial();

    String getDisplayName();
}
