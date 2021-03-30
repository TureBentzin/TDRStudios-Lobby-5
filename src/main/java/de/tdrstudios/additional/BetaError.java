package de.tdrstudios.additional;

import de.bentzin.tools.DevTools;
import de.tdrstudios.lobbyplugin.Chat;
import de.tdrstudios.lobbyplugin.LobbyPlugin;
import de.tdrstudios.lobbyplugin.utils.StopServerOperation;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.PrintStream;
import java.io.PrintWriter;

public class BetaError extends Error{

    public BetaError() {
        System.err.println("You are running beta software without accepting the risk of doing this! So we have to stop the server now!");
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.kickPlayer(Chat.getPrefix() + "\n" + Chat.getErrorColor() + "This server runs an unsupported version of "+ Chat.getAccentColor() + LobbyPlugin.getPlugin().getName() + Chat.getErrorColor() + " and doesn't accept the risks of doing that!" + "\n \n" + Chat.getChatColor() +"The server was terminated immediately!" ); //Beta Kick!
        }
        DevTools.getOperationManager().executeOperation(new StopServerOperation());
    }

    @Override
    public String getMessage() {
        String r = "I´m expecting a flag in config.yml for accepting the risks of beta use!";
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

    @Override
    public StackTraceElement[] getStackTrace() {
        StackTraceElement[] elements = new StackTraceElement[0];
        return elements;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    @Override
    public void setStackTrace(StackTraceElement[] stackTrace) {

    }

    private void shutdown() {
        Bukkit.getServer().shutdown();
        Thread.currentThread().stop();
    }
}
