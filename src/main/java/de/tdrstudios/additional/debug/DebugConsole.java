package de.tdrstudios.additional.debug;

import de.bentzin.tools.console.Console;

public class DebugConsole extends Console {

    protected boolean active = false;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public DebugConsole(Class clazz) {
        super(clazz.getName(),"DEBUG-" + clazz.getSimpleName(), ";");
    }

    /**
     *
     * @param key - for global debug
     */
    public DebugConsole(String key) {
        super(key, key, "");
    }

    protected static final DebugConsole debugConsole = new DebugConsole("global");

    public static DebugConsole getDebugConsole() {
        return debugConsole;
    }


    @Override
    public void send(String msg) {
        if(active)
        super.send(msg);
    }

    @Override
    public void send(String msg, String pPrefix, String pSuffix) {
        if(active)
        super.send(msg, pPrefix, pSuffix);
    }

}
