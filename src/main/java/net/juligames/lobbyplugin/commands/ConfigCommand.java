package net.juligames.lobbyplugin.commands;

import net.juligames.lobbyplugin.Chat;
import net.juligames.lobbyplugin.LobbyPlugin;
import net.juligames.lobbyplugin.msgs.UsageMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConfigCommand implements CommandExecutor {
    private Command command;

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
    public ConfigCommand(String command) {
        setCommand(LobbyPlugin.getPlugin().getCommand(command));
        registerMessages();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Chat chat = new Chat();
        if(sender instanceof Player) {
            Player player = (Player) sender;
            chat.setPlayers(new Player[]{player});
            chat.send(Chat.getAccentColor() + "[DEBUG] "+ Chat.getChatColor()+"This command is only for debug purposes!");
            if(args.length == 1) {
                if(args[0].equalsIgnoreCase("save")) {
                    LobbyPlugin.getPlugin().saveConfig();
                    chat.send(Chat.getAccentColor() + "[DEBUG] "+ Chat.getChatColor()+"Config was saved successfully!");
                } else
                if(args[0].equalsIgnoreCase("read")) {
                    String string = LobbyPlugin.getPlugin().getConfig().saveToString();
                    chat.send(Chat.getAccentColor() + "[DEBUG] "+ Chat.getChatColor()+"Config read was successfully!");
                    chat.send(" ");
                    chat.send(string);
                    chat.send(" ");
                }else {
                    chat.sendMessage(UsageMessage.getNameFIX(getCommand()));
                }
            }else {
                chat.sendMessage(UsageMessage.getNameFIX(getCommand()));
            }
        }
        return true;
    }
    private void registerMessages() {
        LobbyPlugin.getMessageManager().registerMessage(new UsageMessage(getCommand()));
    }
}
