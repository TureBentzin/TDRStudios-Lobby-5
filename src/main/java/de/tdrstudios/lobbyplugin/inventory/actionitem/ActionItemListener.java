package de.tdrstudios.lobbyplugin.inventory.actionitem;

import de.bentzin.tools.console.Console;
import de.tdrstudios.additional.debug.DebugConsole;
import de.tdrstudios.additional.debug.Point;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ActionItemListener implements Listener {


    private static final ActionItemListener instance = new ActionItemListener();

    private ActionItemListener(){

        new Point(Thread.currentThread()).point();
        System.out.println("instance = " + instance);
        console.send("Starting Service!");
    }


    public static ActionItemListener getInstance() {
        return instance;
    }

    private Console console = new Console(this.getClass().getSimpleName(), this.getClass().getSimpleName(), "");

    private List<ActionItem> actionItemList = new ArrayList<ActionItem>();

    protected List<ActionItem> getActionItemList() {
        return actionItemList;
    }


    public void registerActionItem(ActionItem actionItem) {

        if(!actionItemList.contains(actionItem))
            actionItemList.add(actionItem);
        else return;
        console.send("register: " + actionItem.getName());

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

                break;
            }
        }
    }

    @Override
    public String toString() {
        return "ActionItemListener{" +
                "actionItemList=" + actionItemList +
                '}';
    }
}
