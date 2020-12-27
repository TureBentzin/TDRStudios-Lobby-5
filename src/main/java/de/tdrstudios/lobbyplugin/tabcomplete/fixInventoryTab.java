package de.tdrstudios.lobbyplugin.tabcomplete;

import com.sun.org.apache.xerces.internal.xs.StringList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class fixInventoryTab implements TabCompleter {

    private Permission permission;

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Permission getPermission() {
        return permission;
    }

    public fixInventoryTab(String permission) {
        setPermission(new Permission(permission));
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        List<String> tab = new ArrayList<>();
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission(getPermission())) {
                if(args.length == 0) {
                    Collection<String> playernames = new ArrayList<String>();
                    for(Player p : Bukkit.getOnlinePlayers()) {
                        playernames.add(p.getName());
                    }
                    tab.add("*");
                    tab.add("@all");
                    tab.add("all");
                }else {
                    List<Player> players = new ArrayList<>();
                    for(Player p : Bukkit.getOnlinePlayers()) {
                        if(p.getName().startsWith(args[0])) {
                            players.add(p);
                        }
                    }
                    if(check("*" , args[0])) {
                        tab.add("*");
                    }
                    if(check("@all" , args[0])) {
                        tab.add("@all");
                    }
                    if(check("all" , args[0])) {
                        tab.add("all");
                    }

                }
            }else {
                tab.clear();
                tab.add(" ");
            }
        }
        sender.sendMessage(String.valueOf(tab));
        return tab;
    }

    private boolean check(String argument, String args) {
        return argument.startsWith(args);
    }
}


