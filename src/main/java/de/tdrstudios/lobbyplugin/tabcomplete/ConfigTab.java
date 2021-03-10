package de.tdrstudios.lobbyplugin.tabcomplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.List;
/**
 * @deprecated
 * @Archive This code is only here for arcive purposes.
 * @usage no usage
 */
@Deprecated
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
        StringBuilder builder = new StringBuilder();

        for(String s : args) {
            builder.append(s +" ");
        }
        sender.sendMessage("TabComplete erkannt! -> " + label  + " - > " + builder.toString());
        if(sender.hasPermission(getPermission())) {
            if (args.length == 1) {
                if (args[0].startsWith("r"))
                    list.add("read");
                else if (args[0].startsWith("s"))
                    list.add("save");
                else {
                    if (args[0].equalsIgnoreCase("")) {
                        list.add("save");
                        list.add("read");
                    }
                    list.add("");
                }
            }

            return getList();
        }
        return null;
    }
}
