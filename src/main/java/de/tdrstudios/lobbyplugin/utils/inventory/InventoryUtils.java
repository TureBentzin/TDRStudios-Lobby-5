package de.tdrstudios.lobbyplugin.utils.inventory;

import de.tdrstudios.lobbyplugin.LobbyPlugin;
import de.tdrstudios.lobbyplugin.utils.config.ConfigUtils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Inventory utils.
 */
public class InventoryUtils {



    private static FileConfiguration c = ConfigUtils.getConfig();

    /**
     * Gets config.
     *
     * @return the config
     */
    public static FileConfiguration getConfig() {
        return c;
    }

    private static List<InventoryContent> index = new ArrayList<InventoryContent>();

    /**
     * Gets index.
     *
     * @return the index
     */
    public static List<InventoryContent> getIndex() {
        return index;
    }

    /**
     * Register inventory content.
     *
     * @param inventoryContent the inventory content
     */
    public static void registerInventoryContent(InventoryContent inventoryContent){
        index.add(inventoryContent);
    }

    /**
     * Sets inventory to lobbystandart.
     *
     * @param player the player witch inventory should set to lobbystandart
     */
    public static void setInventory(Player player) {
        PlayerInventory playerInventory = player.getInventory();
        playerInventory.clear();
        for(InventoryContent content : getIndex()) {
            playerInventory.setItem(content.getSlot(), content.toItemStack());

        }
    }


    /**
     * Register all inventory contents.
     */
    public static  void registerAllInventoryContents() {
        registerInventoryContent(new InventoryContent("tdrstudios.hotbar.nav.material" ,"tdrstudios.hotbar.nav.displayName" , 1 , c.getInt("tdrstudios.hotbar.nav.slot"))); // Navigator

        registerInventoryContent(new InventoryContent("tdrstudios.hotbar.info.material" ,"tdrstudios.hotbar.info.displayName" , 1 , c.getInt("tdrstudios.hotbar.info.slot"))); // Info

        registerInventoryContent(new InventoryContent("tdrstudios.hotbar.settings.material" ,"tdrstudios.hotbar.settings.displayName" , 1 , c.getInt("tdrstudios.hotbar.settings.slot"))); // Settings

        registerInventoryContent(new InventoryContent("tdrstudios.hotbar.stick.material" ,"tdrstudios.hotbar.stick.displayName" , 1 , c.getInt("tdrstudios.hotbar.stick.slot"))); // HideStick

        registerInventoryContent(getBackItem(), 35);

    }


    //General Inventory Content
    private static InventoryContent backItem = new InventoryContent("tdrstudios.items.back.material" , "tdrstudios.items.back.name", 1, 0);
    public static InventoryContent getBackItem() {
        return backItem;
    }

    protected static Sound openingSound = Sound.BLOCK_BARREL_OPEN; //Soon in config
    public static Sound getOpeningSound() {
        return openingSound;
    }
    public static void playOpeningSound(Player player) {
        player.playSound(player.getLocation() , getOpeningSound() , 30, 1);
    }
}
