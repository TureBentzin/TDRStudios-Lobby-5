package net.juligames.lobbyplugin.commands;

import net.juligames.lobbyplugin.Chat;
import net.juligames.lobbyplugin.LobbyPlugin;
import net.juligames.lobbyplugin.msgs.LackingPermissionMessage;
import net.juligames.lobbyplugin.msgs.MessageManager;
import net.juligames.lobbyplugin.msgs.UsageMessage;
import net.juligames.lobbyplugin.utils.inventory.InventoryUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import tdrstudios.Work_In_Progress;

@Work_In_Progress
public class FixInventoryCommand implements CommandExecutor {
    private Permission permission;
    private Command command;

    protected Command getCommand() {
        return command;
    }

    public Permission getPermission() {
        return permission;
    }

    protected void setCommand(Command command) {
        this.command = command;
    }
    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public FixInventoryCommand(String command ,String permission) {
        setCommand(LobbyPlugin.getPlugin().getCommand(command));
        setPermission(new Permission(permission, "Permission for " + getClass().getName()));
        ; // Change soon
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
                        chat.send(LackingPermissionMessage.getNameFIX(getPermission().getName() + ".other"));
                    }

                    } else {
                        if (args[0] == "all" | args[0] == "*" | args[0] == "@a") {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.playSound(all.getLocation(), Sound.BLOCK_BELL_USE, SoundCategory.VOICE, 30, 2);
                                InventoryUtils.setInventory(all);
                                chat.send(all, Chat.getAccentColor() + player.getName() + Chat.getChatColor() + " has reset your inventory!");
                            }
                        }
                        chat.send("You have reset " + Chat.getAccentColor() + "everyone´s" + Chat.getChatColor() + " inventory!");
                    }
                }else {
                if(args.length == 0) {
                    player.playSound(player.getLocation(), Sound.BLOCK_BELL_USE, SoundCategory.VOICE, 30, 2);
                    chat.sendMessage("You have reset " + Chat.getAccentColor() + "your" + Chat.getChatColor() + " inventory!");
                }else {
                    chat.send(UsageMessage.getNameFIX(getCommand()));
                }
            }
            } else {

            if(sender instanceof ConsoleCommandSender) {
                if(args.length == 1) {
                    if(Bukkit.getPlayer(args[0]) != null) {
                        Player target = Bukkit.getPlayer(args[0]);
                        target.playSound(target.getLocation(), Sound.BLOCK_BELL_USE, SoundCategory.VOICE, 30 ,2);
                        InventoryUtils.setInventory(target);
                    }else {
                     if(args[0] == "all" | args[0] == "*" | args[0] == "@a") {
                        for(Player all : Bukkit.getOnlinePlayers()) {
                            all.playSound(all.getLocation() , Sound.BLOCK_BELL_USE , SoundCategory.VOICE , 30 ,2);
                            InventoryUtils.setInventory(all);
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
        messageManager.registerMessage(new LackingPermissionMessage(getPermission() + ".other"));
        messageManager.registerMessage(new LackingPermissionMessage(getPermission() + ".other.all"));

        messageManager.registerMessage(new UsageMessage(getCommand()));
        getCommand().setPermissionMessage(new Chat().buildSend(getPermission().getName())); // If you see this remove it!
    }
}
