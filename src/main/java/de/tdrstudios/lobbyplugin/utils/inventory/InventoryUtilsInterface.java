package de.tdrstudios.lobbyplugin.utils.inventory;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import javax.naming.directory.InvalidAttributesException;
import java.util.*;
public abstract class InventoryUtilsInterface {

    private static FileConfiguration c;

    private static List<InventoryContent> index = new ArrayList<>();

    public static List<InventoryContent> getIndex() {
        return index;
    }

    public static void setIndex(List<InventoryContent> index) {
        InventoryUtilsInterface.index = index;
    }

    /**
     * @param inventoryContent Register a InventoryContent
     */
    public static boolean registerInventoryContent(InventoryContent inventoryContent) {
        if(!index.contains(inventoryContent)) {
            index.add(inventoryContent);
            return true;
        }else
        return false;
    }
    public static boolean registerInventoryContent(InventoryContent inventoryContent, int slot) {
        if(!index.contains(inventoryContent)) {
            index.add(inventoryContent);
            return true;
        }else
            return false;
    }

    public static boolean removeInventoryContent(InventoryContent inventoryContent) {
        if(index.contains(inventoryContent)) {
            index.remove(inventoryContent);
            return true;
        }else
        return false;
    }
    public static Boolean removeInventoryContent(int slot) throws InvalidAttributesException {

        if(!index.isEmpty())
        for (InventoryContent content : index) {
            if(content.getSlot() == slot) {
                index.remove(content);
                return true;
            }
            else
                return false;
        }
        else
        throw new InvalidAttributesException("The index for " + InventoryUtilsInterface.class.getName() + "is empty");
        return null;
    }

    /**
     * Gets config.
     * @return the config
     */
    public static FileConfiguration getConfig() {
        return c;
    }

    private static Inventory inventory = Bukkit.createInventory(null, InventoryType.CHEST);

    public static Inventory getInventory() {
        for(InventoryContent content : index) {
            inventory.setItem( content.getSlot() ,content.toItemStack());
        }
        return inventory;
    }
    public static void setInventory(Inventory inventory) {
        InventoryUtilsInterface.inventory = inventory;
    }


}
