package de.tdrstudios.lobbyplugin.commands;

import de.tdrstudios.lobbyplugin.Chat;
import de.tdrstudios.lobbyplugin.LobbyPlugin;
import de.tdrstudios.lobbyplugin.msgs.LackingPermissionMessage;
import de.tdrstudios.lobbyplugin.msgs.UsageMessage;
import de.tdrstudios.lobbyplugin.tabcomplete.Argument;
import de.tdrstudios.lobbyplugin.utils.FlyUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.NotNull;

import java.util.*;

//TODO: Add a Command for /fly <Player> <on/off>
public class FlyCommand extends SimpleCommand {

    Permission perm;

    public FlyCommand(Command command, Permission permission) {
        super(command, permission, getNullList());
        perm = permission;
        setArguments(getTabComplete());
    }

    @Override
    public void onSimpleCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            Chat chat = new Chat(p);
            if (sender.hasPermission(perm)) {
                if (args.length != 0) {
                    if (args[0].equalsIgnoreCase("on")) {
                        FlyUtils.setFly(true, p, null);
                        return;
                    }
                    if (args[0].equalsIgnoreCase("off")) {
                        FlyUtils.setFly(false, p, null);
                        return;
                    }
                    Player target = Bukkit.getPlayer(args[0]);

                    if (target != null) {
                        if (args.length == 1) {
                            FlyUtils.toggleFly(target, p);
                        } else {
                            if (args[1].equalsIgnoreCase("on")) {
                                FlyUtils.setFly(true, target, p);
                                return;
                            }
                            if (args[1].equalsIgnoreCase("off")) {
                                FlyUtils.setFly(false, target, p);
                                return;
                            } else {
                                chat.sendMessage(getUsageMessage());
                            }
                        }
                    } else {
                        chat.send(Chat.getErrorColor() + "The Player " + Chat.getAccentColor() + args[0] + Chat.getErrorColor() + " is not online!");
                    }
                } else {
                    FlyUtils.toggleFly(p, null);
                }
            } else {
                chat.sendMessage(new LackingPermissionMessage(perm));
            }
        }
        else {
            Chat chat = new Chat(sender);
            if (sender.hasPermission(perm)) {
                if (args.length != 0) {
                    Player target = Bukkit.getPlayer(args[0]);

                    if (target != null) {
                        if (args.length == 1) {
                            FlyUtils.toggleFly(target, sender);
                        }
                        else {
                            if (args[1] == "on") {
                                FlyUtils.setFly(true, target, sender);
                            }
                            if (args[1] == "off") {
                                FlyUtils.setFly(false, target, sender);
                            }
                            else {
                                chat.sendMessage(getUsageMessage());
                            }
                        }
                    }
                    else {
                        chat.send(Chat.getErrorColor() + "The Player " + Chat.getAccentColor() + args[0] + Chat.getErrorColor() + " is not online!");
                    }
                }
                else {
                    chat.sendMessage(getUsageMessage());
                }
            }
            else {
                chat.sendMessage(new LackingPermissionMessage(perm));
            }
        }
    }

    @Override
    public List<Argument>[] getTabComplete() {
        List<Argument> arguments0 = new ArrayList<>();
        Argument argument = new Argument("%Players%", new Permission("tdrstudios.lobby.perms.fly.other"));
        argument.setPlayermode(true);
        arguments0.add(argument);
        arguments0.add(new Argument("on"));
        arguments0.add(new Argument("off"));
        List<Argument> arguments1 = new ArrayList<>();
        List<Argument> depends = new ArrayList<>();
        depends.add(argument);
        arguments1.add(new Argument("on", new Permission("tdrstudios.lobby.perms.fly.other"), depends));
        arguments1.add(new Argument("off", new Permission("tdrstudios.lobby.perms.fly.other"), depends));
        List<Argument>[] argumentsfinal = new List[2];
        argumentsfinal[0] = arguments0;
        argumentsfinal[1] = arguments1;
        return argumentsfinal;
    }
}
