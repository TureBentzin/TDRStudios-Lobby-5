package de.tdrstudios.lobbyplugin.tabcomplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.List;

public class ConfigTab implements TabCompleter {

    private Permission permission;

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public ConfigTab(Permission permission) {
        setPermission(permission);

    }
    private List<String> list = new ArrayList<String>();
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        for(String s: args) {
            System.out.println(s);
        }
        sender.sendMessage("TabComplete erkannt! -> " + label  + " - > " + args[0]);
        if(sender.hasPermission(getPermission())) {
            if (args.length == 1) {
                if (args[0].startsWith("r"))
                    list.add("read");
                else if (args[0].startsWith("s"))
                    list.add("save");
                else {
                    list.add("");
                }
            } else if (args.length == 0) {
                list.add("save");
                list.add("read");
            }

            return list;
        }
        return null;
    }
}
