package de.tdrstudios.lobbyplugin.commands;

import de.tdrstudios.additional.debug.DebugConsole;
import de.tdrstudios.lobbyplugin.Chat;
import de.tdrstudios.lobbyplugin.LobbyPlugin;
import de.tdrstudios.lobbyplugin.msgs.Message;
import de.tdrstudios.lobbyplugin.msgs.UsageMessage;
import de.tdrstudios.lobbyplugin.tabcomplete.Argument;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.List;

public abstract class SimpleCommand extends MyCommand{


    private CommandSender commandSender = null;
    public CommandSender getCommandSender() {
        return commandSender;
    }
    private void setCommandSender(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    private Message usageMessage = new UsageMessage(getCommand());

    public Message getUsageMessage() {
        return usageMessage;
    }
    public void printUsageMessage() {
        Chat.sendFast(getCommandSender(), getUsageMessage());
    }

    public Chat chat = new Chat();
    protected void setChat(Chat chat) {
        this.chat = chat;
    }
    public Chat getPlayerChat() {
        return chat;
    }

    public SimpleCommand(Command command, Permission permission, List<Argument>[] arguments) {
        super(command, permission, arguments);
        setArguments(getTabComplete());
    }
    public SimpleCommand(Command command, Permission permission, List<Argument>[] arguments, CommandSender commandSender) {
        super(command, permission, arguments);
        setCommandSender(commandSender);
        setArguments(getTabComplete());
    }

    @Override
    public Permission getOtherPermission() {
       return new Permission(getPermission().getName() + ".other", getPermission().getDescription(), getPermission().getDefault());
    }

    public boolean senderIsPlayer = getCommandSender() instanceof Player;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(sender instanceof Player)
            setChat(new Chat((Player) sender));
        setCommandSender(sender);
        DebugConsole.getDebugConsole().send(" Executing SimpleCommand: " + sender.getName() + " is executing " + getCommand().getName());
        try {
            onSimpleCommand(sender, cmd, label, args);
        }catch (Exception e){
            System.out.println("Error in a simple Command: " + e.getMessage());
            e.printStackTrace();
        }

        return true;
    }
    public abstract void onSimpleCommand(CommandSender sender, Command cmd, String label, String[] args);


}
