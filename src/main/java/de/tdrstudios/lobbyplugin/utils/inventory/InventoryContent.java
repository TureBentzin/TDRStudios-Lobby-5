package de.tdrstudios.lobbyplugin.utils.inventory;

import de.tdrstudios.lobbyplugin.utils.config.ConfigUtils;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryContent {

    private boolean staticItem = false;

    public void setStaticItem(boolean staticItem) {
        this.staticItem = staticItem;
    }

    /**
     *
     * @return if the item is for multiple use!
     */
    public boolean isStaticItem() {
        return staticItem;
    }

    public InventoryContent(Material material, String displayName, int count, boolean isEnchant , int slot) {
        setCount(count);
        setMaterial(material);
        setItemStack(new ItemStack(getMaterial(), getCount()));
        setMeta(getItemStack().getItemMeta()); // Fixes #20

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
        try {
            setMaterial(Material.getMaterial(ConfigUtils.getString(ConfigMaterialString)));
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        setItemStack(new ItemStack(getMaterial(), getCount()));

        setMeta(getItemStack().getItemMeta()); //Add in fix #20

        try {
           // System.out.println("[DEBUG ISSUE#20] ConfigMaterialString = " + ConfigMaterialString + ", ConfigdisplayNameString = " + ConfigdisplayNameString + ", count = " + count + ", slot = " + slot);
            //System.out.println("[DEBUG ISSUE#20] Result: " + ConfigUtils.getString(ConfigdisplayNameString));

            getMeta().setDisplayName(ConfigUtils.getString(ConfigdisplayNameString));
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }

        getItemStack().setItemMeta(getMeta());
        setSlot(slot);
    }

    public InventoryContent(String ConfigMaterialString, String ConfigdisplayNameString, int count , int slot , boolean staticItem) {
        setCount(count);
        setStaticItem(staticItem);
        try {
            setMaterial(Material.getMaterial(ConfigUtils.getString(ConfigMaterialString)));
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        setItemStack(new ItemStack(getMaterial(), getCount()));

        setMeta(getItemStack().getItemMeta()); //Add in fix #20

        try {
            // System.out.println("[DEBUG ISSUE#20] ConfigMaterialString = " + ConfigMaterialString + ", ConfigdisplayNameString = " + ConfigdisplayNameString + ", count = " + count + ", slot = " + slot);
            //System.out.println("[DEBUG ISSUE#20] Result: " + ConfigUtils.getString(ConfigdisplayNameString));

            getMeta().setDisplayName(ConfigUtils.getString(ConfigdisplayNameString));
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }

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
