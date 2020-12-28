package de.tdrstudios.lobbyplugin.utils.inventory;

import de.tdrstudios.lobbyplugin.LobbyPlugin;
import de.tdrstudios.lobbyplugin.utils.config.ConfigUtils;
import org.bukkit.Material;
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
     * Legacy: set inventory.
     *
     * @param inventory the Player Inventory that will be set to LobbyInventory
     * @deprecated
     * @implNote This Method is an "relict" and cant be used for the plugin!!!!
     */
    @Deprecated
    public static void setInventory(PlayerInventory inventory) {

        inventory.clear(); // Clear all items out of the inventory
        //List all ItemSacks
        ItemStack item1 = new ItemStack(Material.getMaterial(ConfigUtils.getConfig().getString("tdrstudios.hotbar.nav.material")));
        ItemMeta itemMeta = item1.getItemMeta();
        itemMeta.setDisplayName(ConfigUtils.getConfig().getString("tdrstudios.hotbar.nav.displayName"));
        item1.setItemMeta(itemMeta);
        ItemStack item2 = new ItemStack(Material.getMaterial(c.getString("tdrstudios.hotbar.info.material")));
        ItemMeta itemMeta2 = item2.getItemMeta();
        itemMeta2.setDisplayName(ConfigUtils.getConfig().getString("tdrstudios.hotbar.info.displayName"));
        item2.setItemMeta(itemMeta2);
        ItemStack item3 = new ItemStack(Material.getMaterial(c.getString("tdrstudios.hotbar.settings.material")));
        ItemMeta itemMeta3 = item3.getItemMeta();
        itemMeta3.setDisplayName(ConfigUtils.getConfig().getString("tdrstudios.hotbar.settings.displayName"));
        item3.setItemMeta(itemMeta3);
        ItemStack item4 = new ItemStack(Material.getMaterial(c.getString("tdrstudios.hotbar.stick.material")));
        ItemMeta itemMeta4 = item4.getItemMeta();
        itemMeta4.setDisplayName(ConfigUtils.getConfig().getString("tdrstudios.hotbar.stick.displayName"));
        item4.setItemMeta(itemMeta4);

        //Set the ItemsStacks into the inventory
        inventory.setItem(c.getInt("tdrstudios.hotbar.nav.slot"), item1);
        inventory.setItem(c.getInt("tdrstudios.hotbar.info.slot"), item2);
        inventory.setItem(c.getInt("tdrstudios.hotbar.settings.slot"), item3);
        inventory.setItem(c.getInt("tdrstudios.hotbar.stick.slot"), item4);

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

        registerInventoryContent(new InventoryContent("tdrstudios.hotbar.info.material" ,"tdrstudios.hotbar.info.displayName" , 1 , c.getInt("tdrstudios.hotbar.info.slot"))); // Navigator

        registerInventoryContent(new InventoryContent("tdrstudios.hotbar.settings.material" ,"tdrstudios.hotbar.settings.displayName" , 1 , c.getInt("tdrstudios.hotbar.settings.slot"))); // Navigator

        registerInventoryContent(new InventoryContent("tdrstudios.hotbar.stick.material" ,"tdrstudios.hotbar.stick.displayName" , 1 , c.getInt("tdrstudios.hotbar.stick.slot"))); // Navigator

    }



}
