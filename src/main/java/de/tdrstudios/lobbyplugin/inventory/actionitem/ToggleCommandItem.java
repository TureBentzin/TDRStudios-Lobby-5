package de.tdrstudios.lobbyplugin.inventory.actionitem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

public abstract class ToggleCommandItem extends ActionItem{

    private Command command;

    public Command getCommand() {
        return command;
    }

    public ToggleCommandItem(String name, ItemDefaults defaults, Command command) throws InvalidConfigurationException {
        super(name, defaults);
        this.command = command;
    }

    @Override
    public void onClick(ActionParameter actionParameter) {
        command.execute(actionParameter.getHumanClicker(), command.getName(), new String[0]);
      //  actionParameter.getClicker().sendMessage("Register Toggle: " + getName());
        updateEnchant(actionParameter.getClicker());
    }

    public abstract void updateEnchant( Player player);
}
