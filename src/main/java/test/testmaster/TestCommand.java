package test.testmaster;

import de.tdrstudios.lobbyplugin.Chat;
import de.tdrstudios.lobbyplugin.commands.MyCommand;
import de.tdrstudios.lobbyplugin.msgs.UsageMessage;
import de.tdrstudios.lobbyplugin.tabcomplete.Argument;
import de.tdrstudios.lobbyplugin.utils.KickUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.List;

public class TestCommand extends MyCommand {

    public TestCommand(Command command) {
        super(command, new Permission("test"), getNullList());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        System.out.println(label);
        if(args.length < 1) {
            Chat.sendFast(sender, new UsageMessage(getCommand()));
        }else {
            Player target = Bukkit.getPlayer(args[0]);
            if(target != null) {
                if(args.length > 2) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < args.length; i++) {
                        if(i != 0)
                            stringBuilder.append(args[i] + " ");
                    }
                    KickUtils.kickPlayer(target,  stringBuilder.toString(), null, sender);
                }
                else
                    KickUtils.kickPlayer(target, "kicked from the server!" , null, sender);
            }else {
                Chat.sendFast(sender, Chat.getErrorColor() + "Player " + Chat.getAccentColor() +  args[0] + Chat.getErrorColor() + " is not online!");
            }
         }
        return true;
    }
}
