package de.tdrstudios.lobbyplugin.commands;

import de.tdrstudios.lobbyplugin.Chat;
import de.tdrstudios.lobbyplugin.LobbyPlugin;
import de.tdrstudios.lobbyplugin.msgs.LackingPermissionMessage;
import de.tdrstudios.lobbyplugin.msgs.MessageManager;
import de.tdrstudios.lobbyplugin.msgs.UsageMessage;
import de.tdrstudios.lobbyplugin.tabcomplete.Argument;
import de.tdrstudios.lobbyplugin.tabcomplete.TabComplete;
import de.tdrstudios.lobbyplugin.utils.inventory.InventoryUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import de.tdrstudios.additional.Work_In_Progress;

import java.util.ArrayList;
import java.util.List;

@Work_In_Progress
public class FixInventoryCommand extends MyCommand {

    public FixInventoryCommand(String command ,String permission) {
        super(LobbyPlugin.getPlugin().getCommand(command), new Permission(permission), getNullList());
        setCommand(LobbyPlugin.getPlugin().getCommand(command));
        setPermission(new Permission(permission, "Permission for " + getClass().getName()));
        ; // Change soon
        List<Argument>[] argumentListArray = new List[1];
        List<Argument> argumentList = new ArrayList<>();
        argumentList.add(new Argument("%Players%"));
        argumentList.add(new Argument("*"));
        argumentList.add(new Argument("all"));
        argumentList.add(new Argument("@a"));
        argumentListArray[0] = argumentList;
        setArguments(argumentListArray);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        registerMessages();
        if(sender instanceof Player) {
            Player player = (Player) sender;
            Chat chat = new Chat();
            chat.setPlayers(new Player[]{player});
            if (args.length == 1) {

                if (Bukkit.getPlayer(args[0]) != null) {
                    if (player.hasPermission(getPermission().getName() + ".other")) {
                        Player target = Bukkit.getPlayer(args[0]);
                        target.playSound(target.getLocation(), Sound.BLOCK_BELL_USE, SoundCategory.VOICE, 30, 2);
                        InventoryUtils.setInventory(target);
                        chat.sendMessage("You have reset " + Chat.getAccentColor() + target.getDisplayName() + Chat.getChatColor() + "´s inventory!");
                        chat.send(target, Chat.getAccentColor() + player.getName() + Chat.getChatColor() + " has reset your inventory!");
                    }else {
                        chat.sendMessage(LackingPermissionMessage.getNameFIX(getPermission().getName() + ".other"));
                    }

                    } else {
                        if (args[0].equalsIgnoreCase("all") | args[0].equalsIgnoreCase("*") | args[0].equalsIgnoreCase("@a")) {
                            if(player.hasPermission(getPermission().getName() + ".other.all")) {
                                for (Player all : Bukkit.getOnlinePlayers()) {
                                    all.playSound(all.getLocation(), Sound.BLOCK_BELL_USE, SoundCategory.VOICE, 30, 2);
                                    InventoryUtils.setInventory(all);
                                    chat.send(all, Chat.getAccentColor() + player.getName() + Chat.getChatColor() + " has reset your inventory!");
                                }
                                chat.send("You have reset " + Chat.getAccentColor() + "everyone´s" + Chat.getChatColor() + " inventory!");
                            }else {
                                chat.sendMessage(LackingPermissionMessage.getNameFIX(getPermission().getName() + ".other.all"));
                                System.out.println("FixInventoryCommand.onCommand");

                            }
                        }else {
                            chat.sendMessage(UsageMessage.getNameFIX(getCommand()));
                        }

                    }
                }else {
                if(args.length == 0) {
                    if(player.hasPermission(getPermission())) {
                        player.playSound(player.getLocation(), Sound.BLOCK_BELL_USE, SoundCategory.VOICE, 30, 2);
                        chat.sendMessage("You have reset " + Chat.getAccentColor() + "your" + Chat.getChatColor() + " inventory!");
                        InventoryUtils.setInventory(player);
                    }else {
                        chat.sendMessage(LackingPermissionMessage.getNameFIX(getPermission()));
                    }
                }else {
                    chat.sendMessage(UsageMessage.getNameFIX(getCommand()));
                }
            }
            } else {

            if(sender instanceof ConsoleCommandSender) {
                Chat chat = new Chat();
                if(args.length == 1) {
                    if(Bukkit.getPlayer(args[0]) != null) {
                        Player target = Bukkit.getPlayer(args[0]);
                        target.playSound(target.getLocation(), Sound.BLOCK_BELL_USE, SoundCategory.VOICE, 30 ,2);
                        InventoryUtils.setInventory(target);
                        chat.send(target, "The " + Chat.getAccentColor() + "Console" + Chat.getChatColor() + " has reset your inventory!");
                        sender.sendMessage(target.getName() + "´s inventory was set to LobbyStandart!");
                    }else {
                     if(args[0] == "all" | args[0] == "*" | args[0] == "@a") {
                        for(Player all : Bukkit.getOnlinePlayers()) {
                            all.playSound(all.getLocation() , Sound.BLOCK_BELL_USE , SoundCategory.VOICE , 30 ,2);
                            InventoryUtils.setInventory(all);
                            chat.send(all, "The " + Chat.getAccentColor() + "Console" + Chat.getChatColor() + " has reset your inventory!");
                        }
                        sender.sendMessage("Everyone´s inventory was set to LobbyStandart!");
                     }
                    }
                }else {
                    sender.sendMessage("Please care about the usage: /fixinventory [Player/'*']");
                }
            }else {
                sender.sendMessage("Error: You cant execute this! -- this is a Problem of your sendertype! Report this error to the Devs if you aren´t using an CommandBlock!");
            }
        }
        return true;
    }

    public void registerMessages() {
        MessageManager messageManager = LobbyPlugin.getMessageManager();
        messageManager.registerMessage(new LackingPermissionMessage(getPermission()));
        messageManager.registerMessage(new LackingPermissionMessage(getPermission().getName() + ".other"));
        messageManager.registerMessage(new LackingPermissionMessage(getPermission().getName() + ".other.all"));

        messageManager.registerMessage(new UsageMessage(getCommand()));
        getCommand().setPermissionMessage(new Chat().buildSend(getPermission().getName())); // If you see this remove it!
    }
}
