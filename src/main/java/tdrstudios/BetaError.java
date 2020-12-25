package tdrstudios;

public class BetaError extends Error{

    public BetaError() {
        System.err.println("You are running a beta software without accepting the risk of doing this!");
    }

    @Override
    public String getMessage() {
        String r = super.getMessage() + "\n This Software inst complete yet!";
        return r;
    }
}
