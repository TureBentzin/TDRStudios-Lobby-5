package de.tdrstudios.lobbyplugin.inventory.actionitem;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

public interface ActionParameter {
    Player getClicker();

    InventoryAction getInventoryAction();

    HumanEntity getHumanClicker();

    ClickType getClickType();

    @Deprecated
    InventoryClickEvent getEvent();
}
