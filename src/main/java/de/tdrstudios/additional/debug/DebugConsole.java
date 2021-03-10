package de.tdrstudios.additional.debug;

import de.bentzin.tools.console.Console;

import java.lang.reflect.Method;

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
}
