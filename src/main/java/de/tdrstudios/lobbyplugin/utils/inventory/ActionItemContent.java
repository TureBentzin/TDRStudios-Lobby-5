package de.tdrstudios.lobbyplugin.utils.inventory;

import de.tdrstudios.lobbyplugin.inventory.actionitem.ActionItem;
import org.bukkit.Material;
import org.bukkit.permissions.Permission;

public class ActionItemContent extends PermissionInventoryContent{
    public ActionItemContent(ActionItem actionItem) {
        super(actionItem.getMaterial(), actionItem.getDisplayName(), actionItem.getCount(), actionItem.isActive(), actionItem.getSlot());
        actionItem.addActionItemContent(this);
    }

    public ActionItemContent(ActionItem actionItem, Permission permission) {
        super(actionItem.getMaterial(), actionItem.getDisplayName(), actionItem.getCount(), actionItem.isActive(), actionItem.getSlot());
        actionItem.addActionItemContent(this);
        setPermission(permission);
    }

    public ActionItemContent(ActionItem actionItem, String permission) {
        super(actionItem.getMaterial(), actionItem.getDisplayName(), actionItem.getCount(), actionItem.isActive(), actionItem.getSlot());
        actionItem.addActionItemContent(this);
        setPermission(permission);
    }



}
