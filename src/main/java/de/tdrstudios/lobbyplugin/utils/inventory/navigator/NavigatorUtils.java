package de.tdrstudios.lobbyplugin.utils.inventory.navigator;

import de.tdrstudios.lobbyplugin.utils.inventory.InventoryContent;
import de.tdrstudios.lobbyplugin.utils.inventory.InventoryUtilsInterface;

public class NavigatorUtils  implements InventoryUtilsInterface {

    /**
     * @param inventoryContent Register a InventoryContent
     */
    @Override
    public void registerInventoryContent(InventoryContent inventoryContent) {

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
