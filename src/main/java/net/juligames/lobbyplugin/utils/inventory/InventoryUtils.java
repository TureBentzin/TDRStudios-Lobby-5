package net.juligames.lobbyplugin.utils.inventory;

import net.juligames.lobbyplugin.utils.config.ConfigUtils;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryUtils {

    private static FileConfiguration c = ConfigUtils.getConfig();

    public static FileConfiguration getConfig() {
        return c;
    }

    /**
     *
     * @param inventory the Player Inventory that will be set to LobbyInventory
     */
    public static void setInventory(PlayerInventory inventory) {
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
        itemMeta3.setDisplayName(ConfigUtils.getConfig().getString("tdrstudios.hotbar.settings.displayName")));
        item3.setItemMeta(itemMeta3);
        ItemStack item4 = new ItemStack(Material.getMaterial(c.getString("tdrstudios.hotbar.stick.material")));
        ItemMeta itemMeta4 = item4.getItemMeta();
        itemMeta4.setDisplayName(ConfigUtils.getConfig().getString("tdrstudios.hotbar.stick.displayName")));
        item4.setItemMeta(itemMeta4);

        //Set the ItemsStacks into the inventory
        inventory.setItem(c.getInt("tdrstudios.hotbar.nav.slot"), item1);
        inventory.setItem(c.getInt("tdrstudios.hotbar.info.slot"), item2);
        inventory.setItem(c.getInt("tdrstudios.hotbar.settings.slot"), item3);
        inventory.setItem(c.getInt("tdrstudios.hotbar.stick.slot"), item4);
    }

    public static void setInventory(Player player) {
        setInventory(player.getInventory());
    }
}
