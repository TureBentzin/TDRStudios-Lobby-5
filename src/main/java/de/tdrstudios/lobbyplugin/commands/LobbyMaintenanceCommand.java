package de.tdrstudios.lobbyplugin.commands;

import de.tdrstudios.lobbyplugin.Chat;
import de.tdrstudios.lobbyplugin.LobbyPlugin;
import de.tdrstudios.lobbyplugin.msgs.Message;
import de.tdrstudios.lobbyplugin.tabcomplete.Argument;
import de.tdrstudios.lobbyplugin.utils.config.ConfigUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LobbyMaintenanceCommand extends MyCommand {


    // STOPSHIP: 12.01.2021
    /**
     * @param command
     * @param permission
     * @param arguments
     * @STOPSHIP: 12.01.2021
     * @Autonom This is for maintenance not for other weird stuff! Thanks!
     */
    public LobbyMaintenanceCommand(Command command, Permission permission, List<Argument>[] arguments) {
        super(command, permission, arguments);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(ConfigUtils.getBoolean("tdrstudios.feature.maintenance.enable")) {
            if (sender instanceof Player) {


            } else if (sender instanceof ConsoleCommandSender) {

            } else {

            }
        }else {
            sender.sendMessage(Chat.getPrefix() + Chat.getErrorColor() + ConfigUtils.getString("tdrstudios.feature.msg.disabled"));
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
        return null;
    }

    public void registerMessages() {
        LobbyPlugin.getMessageManager().registerMessage(new Message("tdrstudios.feature.maintenance.enable" , ConfigUtils.getString("tdrstudios.feature.maintenance.msg.enabled")));

    }


    @Override
    public void generatePermission(String permission) {

    }

    @Override
    public List<String>[] registerTabComplete() {
        return new List[0];
    }
}
