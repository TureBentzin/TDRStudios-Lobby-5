package de.tdrstudios.lobbyplugin.inventory.actionitem;

import de.bentzin.tools.console.Console;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ActionItemListener implements Listener {


    private ActionItemListener(){
        getInstance().console.send("Starting Service!");
    };
    private static final ActionItemListener instance = new ActionItemListener();

    public static ActionItemListener getInstance() {
        return instance;
    }

    private Console console = new Console(this.getClass().getName(), this.getClass().getName(), "");

    private List<ActionItem> actionItemList = new ArrayList<ActionItem>();

    protected List<ActionItem> getActionItemList() {
        return actionItemList;
    }


    public void registerActionItem(ActionItem actionItem) {
        if(!actionItemList.contains(actionItem))
            actionItemList.add(actionItem);
    }

    public boolean isRegisterd(ActionItem actionItem)  {
        return getActionItemList().contains(actionItem);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ItemStack stack = event.getCurrentItem();

        for (ActionItem actionItem : getActionItemList()) {
            if(actionItem.isSimilar(stack)) {
                actionItem.onClick(new ActionItem.ActionParameterClass(event));
                console.send("Execute: " + actionItem.getName() + ":" + "onClick()");
                break;
            }
        }
    }
}
