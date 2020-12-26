package net.juligames.lobbyplugin.utils.inventory;

import net.juligames.lobbyplugin.utils.config.ConfigUtils;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class InventoryUtils {



    private static FileConfiguration c = ConfigUtils.getConfig();

    public static FileConfiguration getConfig() {
        return c;
    }

    private static List<InventoryContent> index = new ArrayList<InventoryContent>();

    public static List<InventoryContent> getIndex() {
        return index;
    }
    public static void registerInventoryContent(InventoryContent inventoryContent){
        index.add(inventoryContent);
    }

    /**
     *
     * @param inventory the Player Inventory that will be set to LobbyInventory
     * @deprecated
     */
    @Deprecated
    public static void LEGACY_setInventory(PlayerInventory inventory) {
        inventory.clear(); // Clear all Items out of the Inventory

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
     *
     * @param playerInventory
     */
    public static void setInventory(PlayerInventory playerInventory) {
        for(InventoryContent content : getIndex()) {
            playerInventory.setItem(content.getSlot(), content.toItemStack());
        }
    }

    public static void setInventory(Player player) {
        setInventory(player.getInventory());
    }

    public static  void registerAllInventoryContents() {
        registerInventoryContent(new InventoryContent("tdrstudios.hotbar.nav.material" ,"tdrstudios.hotbar.nav.displayName" , 1 , c.getInt("tdrstudios.hotbar.nav.slot"))); // Navigator

        registerInventoryContent(new InventoryContent("tdrstudios.hotbar.info.material" ,"tdrstudios.hotbar.info.displayName" , 1 , c.getInt("tdrstudios.hotbar.info.slot"))); // Navigator

        registerInventoryContent(new InventoryContent("tdrstudios.hotbar.settings.material" ,"tdrstudios.hotbar.settings.displayName" , 1 , c.getInt("tdrstudios.hotbar.settings.nav.slot"))); // Navigator

        registerInventoryContent(new InventoryContent("tdrstudios.hotbar.stick.material" ,"tdrstudios.hotbar.stick.displayName" , 1 , c.getInt("tdrstudios.hotbar.stick.slot"))); // Navigator

    }



}
