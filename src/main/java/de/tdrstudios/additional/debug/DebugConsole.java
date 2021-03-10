package de.tdrstudios.additional.debug;

import de.bentzin.tools.console.Console;

public class DebugConsole extends Console {
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
}
