package de.tdrstudios.lobbyplugin.events;

import de.bentzin.tools.task.Operation;
import org.bukkit.entity.Player;

public class HideStickCooldownOperation extends Operation {

    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public HideStickCooldownOperation(Player player) {
        super(player.getDisplayName() + "cooldown", false);
        setPlayer(player);
    }

    @Override
    public void run() {
        GeneralEvents.getInstance().cooldown.remove(player.getUniqueId());
        System.out.println(player.getName() + " removed from cooldown");
        player.sendMessage("DEBUG: Du bist freigeschaltet (DE)");
    }
}
