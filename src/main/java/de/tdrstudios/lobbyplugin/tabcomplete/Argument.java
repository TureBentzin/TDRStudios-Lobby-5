package de.tdrstudios.lobbyplugin.tabcomplete;

import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.List;

public class Argument {

    public Argument(String argument) {
        setArgument(argument);
        setPermission(new Permission("tdrstudios.lobby.player"));
    }
    public Argument(String argument, Permission permission) {
        setArgument(argument);
        setPermission(permission);
    }
    public Argument(String argument, Permission permission, List<Argument> depends) {
        setArgument(argument);
        setPermission(permission);
        setDepends(depends);
    }


    private String argument;
    private Permission permission;
    private boolean playermode = false;

    public boolean isPlayermode() {
        return playermode;
    }

    public void setPlayermode(boolean playermode) {
        this.playermode = playermode;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public String getArgument() {
        return argument;
    }
    private List<Argument> depends;

    public void setDepends(List<Argument> depends) {
        this.depends = depends;
    }

    public List<Argument> getDepends() {
        return depends;
    }
    public void addDepends(Argument argument) {
        getDepends().add(argument);
    }

    public boolean hasDepends() {
        if(getDepends() != null)
        return true;
        else return false;
    }

    @Override
    public String toString() {
        return "Argument{" +
                "argument='" + argument + '\'' +
                '}' +" \n";

    }
}
