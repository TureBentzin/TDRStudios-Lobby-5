package de.tdrstudios.lobbyplugin.utils.inventory;
import org.bukkit.configuration.file.FileConfiguration;

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

    public static boolean removeInventoryContent(InventoryContent inventoryContent) {
        if(index.contains(inventoryContent)) {
            index.remove(inventoryContent);
            return true;
        }else
        return false;
    }

    /**
     * Gets config.
     * @return the config
     */
    public static FileConfiguration getConfig() {
        return c;
    }

}
