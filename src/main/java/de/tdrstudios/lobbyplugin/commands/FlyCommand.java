package de.tdrstudios.lobbyplugin.commands;

import de.tdrstudios.additional.Work_In_Progress;
import de.tdrstudios.lobbyplugin.Chat;
import de.tdrstudios.lobbyplugin.LobbyPlugin;
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

    public FlyCommand(Command command, Permission permission, List<Argument>[] arguments) {
        super(command, permission, arguments);
        perm = permission;
    }

    @Override
    @Work_In_Progress
    public void onSimpleCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (senderIsPlayer) {
            Player p = (Player) sender;
            if (sender.hasPermission(perm)) {
                if (args.length != 0) {
                    Player target = Bukkit.getPlayer(args[0]);

                    if (target != null) {
                        if (args.length == 1) {
                            FlyUtils.toggleFly(target, p);
                        }
                        else {
                            if (args[1] == "on") {
                                if (!target.isFlying()) {
                                    //fliegt jetzt
                                    target.setFlying(true);
                                    return;
                                }
                                else {
                                    //msg fliegt schon
                                    return;
                                }
                            }
                            if (args[1] == "off") {
                                if (target.isFlying()) {
                                    //fliegt jetzt nicht mehr
                                    target.setFlying(false);
                                    return;
                                }
                                else {
                                    //msg fliegt nicht
                                    return;
                                }
                            }
                            //falsches argument
                        }
                    }
                    else {
                        //spieler nicht online
                    }
                }
                else {
                    if (p.isFlying()) {
                        p.setFlying(false);
                        //du fliegst nicht mehr
                    }
                    else {
                        p.setFlying(true);
                        //du fliegst jetzt
                    }
                }
            }
            else {
                //keine rechte
            }
        }
        else {
            if (sender.hasPermission(perm)) {
                if (args.length != 0) {
                    Player target = Bukkit.getPlayer(args[0]);

                    if (target != null) {
                        if (args.length == 1) {
                            if(target.isFlying()) {
                                target.setFlying(false);
                                //fliegt nicht mehr
                            }
                            else {
                                target.setFlying(true);
                                //fliegt jetzt
                            }
                        }
                        else {
                            if (args[1] == "on") {
                                if (!target.isFlying()) {
                                    //fliegt jetzt
                                    target.setFlying(true);
                                    return;
                                }
                                else {
                                    //msg fliegt schon
                                    return;
                                }
                            }
                            if (args[1] == "off") {
                                if (target.isFlying()) {
                                    //fliegt jetzt nicht mehr
                                    target.setFlying(false);
                                    return;
                                }
                                else {
                                    //msg fliegt nicht
                                    return;
                                }
                            }
                            //falsches argument
                        }
                    }
                    else {
                        //spieler nicht online
                    }
                }
                else {
                    //du must einen spieler angeben
                }
            }
            else {
                //keine rechte
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
