package de.tdrstudios.lobbyplugin.commands;

import de.tdrstudios.lobbyplugin.Chat;
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
    }

    public boolean senderIsPlayer = getCommandSender() instanceof Player;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        onSimpleCommand(sender, cmd, label, args);
        if(sender instanceof Player)
            setChat(new Chat((Player) sender));
        return true;
    }
    public abstract void onSimpleCommand(CommandSender sender, Command cmd, String label, String[] args);


}
