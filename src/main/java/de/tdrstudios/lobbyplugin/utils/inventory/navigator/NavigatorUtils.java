package de.tdrstudios.lobbyplugin.utils.inventory.navigator;

import com.sun.deploy.security.CertStore;
import de.tdrstudios.lobbyplugin.utils.config.ConfigUtils;
import de.tdrstudios.lobbyplugin.utils.inventory.InventoryContent;
import de.tdrstudios.lobbyplugin.utils.inventory.InventoryUtilsInterface;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import sun.security.krb5.Config;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;
import java.util.*;

public class NavigatorUtils extends InventoryUtilsInterface {


    private static FileConfiguration c;

    private static List<InventoryContent> index = new ArrayList<>();

    public static List<InventoryContent> getIndex() {
        return index;
    }

    public static void setIndex(List<InventoryContent> index) {
       NavigatorUtils.index = index;
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
       NavigatorUtils.inventory = inventory;
    }

    public static void registerAllInventoryContents() {
        String rootPattern = "tdrstudios.inventorys.nav.items.";
        registerInventoryContent(getContentFromConfig(rootPattern + "spawn"));
        registerInventoryContent(getContentFromConfig(rootPattern + "MiniGame1")); // sw
        registerInventoryContent(getContentFromConfig(rootPattern + "MiniGame2")); // bw
        registerInventoryContent(getContentFromConfig(rootPattern + "MiniGame3")); // cb
        registerInventoryContent(getContentFromConfig(rootPattern + "MiniGame4")); // fb
        registerInventoryContent(getContentFromConfig(rootPattern + "MiniGame5")); // KFFA
    }

    //Example: tdrstudios.inventorys.nav.items.spawn.material
    //Root   : tdrstudios.inventorys.nav.items.spawn
    private static InventoryContent getContentFromConfig(String root) {
        try {
            if (ConfigUtils.getString(root + ".material") != null) {}else
            {
                ConfigUtils.registerConfiguration(root + ".material");
                ConfigUtils.registerConfiguration(root + ".name");
                ConfigUtils.registerConfiguration(root + ".count");
            }
        }catch (InvalidConfigurationException exception){
            exception.printStackTrace();
        }

        return new InventoryContent(root + ".material" ,root + ".name", ConfigUtils.getConfig().getInt(root + ".count"),  ConfigUtils.getConfig().getInt(root + ".slot"));

    }





    //    public void openGUI(Player player) {
    //    ItemStack netherstar = new ItemStack(Material.NETHER_STAR);
    //    ItemMeta itemMetaNetherstar = netherstar.getItemMeta();
    //    itemMetaNetherstar.setDisplayName("§lSpawn");
    //    netherstar.setItemMeta(itemMetaNetherstar);

    //    ItemStack slimeball = new ItemStack(Material.GRASS);
    //    ItemMeta itemMetaSlimeball = slimeball.getItemMeta();
    //    itemMetaSlimeball.setDisplayName("§lSkywars");
    //    slimeball.setItemMeta(itemMetaSlimeball);

    //    ItemStack BED = new ItemStack(Material.RED_BED);
    //    ItemMeta itemMetaBED = BED.getItemMeta();
    //    itemMetaBED.setDisplayName("§lBedWars");
    //    BED.setItemMeta(itemMetaBED);

    //    ItemStack back = new ItemStack(Material.ARROW);
    //    ItemMeta itemMetaBack = back.getItemMeta();
    //    itemMetaBack.setDisplayName("§lZurück");
    //    back.setItemMeta(itemMetaBack);

    //    ItemStack diamond = new ItemStack(Material.DIAMOND_PICKAXE);
    //    ItemMeta itemMetaDiamond = diamond.getItemMeta();
    //    itemMetaDiamond.setDisplayName("§lCitybuild");
    //    diamond.setItemMeta(itemMetaDiamond);

    //    ItemStack diamondaxe = new ItemStack(Material.DIAMOND_AXE);
    //    ItemMeta itemMetaDiamondaxe = diamondaxe.getItemMeta();
    //    itemMetaDiamondaxe.setDisplayName("§lFreeBuild");
    //    diamondaxe.setItemMeta(itemMetaDiamondaxe);

    //    ItemStack diamondsword = new ItemStack(Material.DIAMOND_SWORD);
    //    ItemMeta itemMetaDiamondsword = diamondsword.getItemMeta();
    //    itemMetaDiamondsword.setDisplayName("§lKnockbackffa");
    //    diamondsword.setItemMeta(itemMetaDiamondsword);

    //    Inventory inventory = Bukkit.createInventory(null, 27, getGUI_NAME());
    //    inventory.setItem(13, new ItemStack(netherstar));
    //    inventory.setItem(10, new ItemStack(slimeball));
    //    inventory.setItem(16, new ItemStack(BED));
    //    inventory.setItem(26, new ItemStack(back));
    //    inventory.setItem(0, new ItemStack(diamond));
    //    inventory.setItem(8, new ItemStack(diamondaxe));
    //    inventory.setItem(18, new ItemStack(diamondsword));
    //    player.openInventory(inventory);
    //  }
}
