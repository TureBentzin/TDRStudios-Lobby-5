package de.tdrstudios.additional;

import de.bentzin.tools.DevTools;
import de.tdrstudios.lobbyplugin.Chat;
import de.tdrstudios.lobbyplugin.LobbyPlugin;
import de.tdrstudios.lobbyplugin.utils.StopServerOperation;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.PrintStream;
import java.io.PrintWriter;

public class BetaError extends Error{

    public BetaError() {
        System.err.println("You are running a beta software without accepting the risk of doing this! So we have to stop the server!");
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.kickPlayer(Chat.getPrefix() + "\n" + Chat.getErrorColor() + "This server runs a unsupported version of "+ Chat.getAccentColor() + LobbyPlugin.getPlugin().getName() + Chat.getErrorColor() + " and don't accept the risks of doing that!" + "\"" + Chat.getChatColor() +"your TDRStudios Team" ); //Beta Kick!
        }
        DevTools.getOperationManager().executeOperation(new StopServerOperation());
    }

    @Override
    public String getMessage() {
        String r =  "I´m expecting a flag in config.yml for accepting the risks of beta use!";
        return r;
    }

    @Override
    public void printStackTrace() {
        System.out.println(getMessage());
        shutdown();
    }

    @Override
    public void printStackTrace(PrintWriter s) {
       s.println(getMessage());
        shutdown();
    }

    @Override
    public void printStackTrace(PrintStream s) {
        s.println(getMessage());
        shutdown();
    }
    private void shutdown() {
        Bukkit.getServer().shutdown();
    }
}
