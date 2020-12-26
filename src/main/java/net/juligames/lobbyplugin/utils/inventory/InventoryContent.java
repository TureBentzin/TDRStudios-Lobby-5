package net.juligames.lobbyplugin.utils.inventory;

import net.juligames.lobbyplugin.utils.config.ConfigUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryContent {

    public InventoryContent(Material material, String displayName, int count, boolean isEnchant , int slot) {
        setCount(count);
        setMaterial(material);
        setItemStack(new ItemStack(getMaterial(), getCount()));

        if(isEnchant) {
            getMeta().addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL , 1 , true);
        }
        getMeta().setDisplayName(displayName);

        getItemStack().setItemMeta(getMeta());
        setSlot(slot);
    }

    public InventoryContent(String ConfigMaterialString, String displayName, int count, boolean isEnchant , int slot) {
        setCount(count);
        setMaterial(Material.getMaterial(ConfigUtils.getConfig().getString(ConfigMaterialString)));
        setItemStack(new ItemStack(getMaterial(), getCount()));

        if(isEnchant) {
            getMeta().addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL , 1 , true);
        }
        getMeta().setDisplayName(displayName);

        getItemStack().setItemMeta(getMeta());
        setSlot(slot);
    }

    public InventoryContent(String ConfigMaterialString, String ConfigdisplayNameString, int count , int slot) {
        setCount(count);
        setMaterial(Material.getMaterial(ConfigUtils.getConfig().getString(ConfigMaterialString)));
        setItemStack(new ItemStack(getMaterial(), getCount()));

        getMeta().setDisplayName(ConfigUtils.getConfig().getString(ConfigdisplayNameString));

        getItemStack().setItemMeta(getMeta());
        setSlot(slot);
    }



    private int count;
    private ItemStack itemStack;
    private Material material;
    private ItemMeta meta;

    private int slot;

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public void setMeta(ItemMeta meta) {
        this.meta = meta;
    }

    public ItemMeta getMeta() {
        return meta;
    }

    public int getCount() {
        return count;
    }

    private ItemStack getItemStack() {
        return itemStack;
    }

    public Material getMaterial() {
        return material;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "InventoryContent{" +
                "count=" + count +
                ", itemStack=" + itemStack +
                ", material=" + material +
                ", meta=" + meta +
                '}';
    }

    public ItemStack toItemStack() { return getItemStack();}
}
