package de.tdrstudios.lobbyplugin.utils.inventory;

import org.bukkit.Material;
import org.bukkit.permissions.Permission;

public abstract class PermissionInventoryContent extends InventoryContent{

    private Permission permission;

    public PermissionInventoryContent(Material material, String displayName, int count, boolean isEnchant, int slot) {
        super(material, displayName, count, isEnchant, slot);
    }

    public PermissionInventoryContent(String ConfigMaterialString, String displayName, int count, boolean isEnchant, int slot) {
        super(ConfigMaterialString, displayName, count, isEnchant, slot);
    }

    public PermissionInventoryContent(String ConfigMaterialString, String ConfigdisplayNameString, int count, int slot) {
        super(ConfigMaterialString, ConfigdisplayNameString, count, slot);
    }

    public PermissionInventoryContent(String ConfigMaterialString, String ConfigdisplayNameString, int count, int slot, boolean staticItem) {
        super(ConfigMaterialString, ConfigdisplayNameString, count, slot, staticItem);
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public void setPermission(String permission) {
        this.permission = new Permission(permission);
    }

}
