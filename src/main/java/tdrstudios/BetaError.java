package tdrstudios;

import org.bukkit.Bukkit;

import java.io.PrintStream;
import java.io.PrintWriter;

public class BetaError extends Error{

    public BetaError() {
        System.err.println("You are running a beta software without accepting the risk of doing this!");
    }

    @Override
    public String getMessage() {
        String r = super.getMessage() + "\n This Software inst complete yet!";
        return r;
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
        shutdown();
    }

    @Override
    public void printStackTrace(PrintWriter s) {
        super.printStackTrace(s);
        shutdown();
    }

    @Override
    public void printStackTrace(PrintStream s) {
        super.printStackTrace(s);
        shutdown();
    }
    private void shutdown() {
        Bukkit.getServer().shutdown();
    }
}
