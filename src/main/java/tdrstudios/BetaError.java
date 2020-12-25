package tdrstudios;

import de.bentzin.tools.DevTools;
import de.bentzin.tools.time.Timing;
import net.juligames.lobbyplugin.utils.StopServerOperation;
import org.bukkit.Bukkit;

import java.io.PrintStream;
import java.io.PrintWriter;

public class BetaError extends Error{

    public BetaError() {
        System.err.println("You are running a beta software without accepting the risk of doing this! So we have to stop the server!");

        DevTools.getOperationManager().executeOperation(new StopServerOperation());
    }

    @Override
    public String getMessage() {
        String r =  " This Software inst complete yet!";
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
