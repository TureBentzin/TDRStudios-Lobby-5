package de.tdrstudios.lobbyplugin.commands;

import de.tdrstudios.lobbyplugin.Chat;
import de.tdrstudios.lobbyplugin.LobbyPlugin;
import de.tdrstudios.lobbyplugin.enums.SenderType;
import de.tdrstudios.lobbyplugin.msgs.LackingPermissionMessage;
import de.tdrstudios.lobbyplugin.msgs.OnlySenderMessage;
import de.tdrstudios.lobbyplugin.tabcomplete.Argument;
import de.tdrstudios.lobbyplugin.utils.SoundUtils;
import de.tdrstudios.lobbyplugin.utils.config.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class GlowCommand extends SimpleCommand {

    public static final boolean AMBIENT = true;
    public static final PotionEffect GLOW_EFFECT = new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1, GlowCommand.AMBIENT, false, false);

    public static Sound TOGGLE_SOUND = SoundUtils.getToggleSound();

    public GlowCommand(String command, Permission permission) {
        super(LobbyPlugin.getPlugin().getCommand(command), permission, getNullList());
        setArguments(getTabComplete());
    }

    @Override
    public List<Argument>[] getTabComplete() {
        List<Argument> arguments1 = new ArrayList<>();
        List<Argument> arguments2 = new ArrayList<>();
        arguments1.add(new Argument("on", getPermission()));
        arguments1.add(new Argument("off", getPermission()));
        Argument players = new Argument("%Players%", new Permission(getPermission().getName() + ".other"));
        arguments1.add(players);
        arguments2.add(new Argument("on", getOtherPermission(), new Argument[]{players}));
        arguments2.add(new Argument("off", getOtherPermission(), new Argument[]{players}));


        return new List[]{arguments1,arguments2};
    }

    @Override
    public void onSimpleCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {
            if (sender.hasPermission(getPermission())) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    toggleGlow(player);
                } else {
                    Chat.sendFast(getCommandSender(), new OnlySenderMessage(SenderType.PLAYER));
                    // System.out.println(sender.getName() + " tried to execute " + getCommand().getName() + " but he is not the right Type!");
                }
            } else {
                Chat.sendFast(getCommandSender(), new LackingPermissionMessage(getPermission()));
            }
            return;
        } else if (args.length == 1) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args[0].equalsIgnoreCase("on")) {
                    if (isGlowing(player))
                        chat.send(Chat.getErrorColor() + ConfigUtils.getString("tdrstudios.commands.glow.msgs.fail.already-me").replace("%Status%", Chat.getAccentColor() + "on" + Chat.getErrorColor()));
                    else
                        setGlow(player, true);
                    return;
                } else if (args[0].equalsIgnoreCase("off")) {
                    if (!isGlowing(player))
                        chat.send(Chat.getErrorColor() + ConfigUtils.getString("tdrstudios.commands.glow.msgs.fail.already-me").replace("%Status%", Chat.getAccentColor() + "off" + Chat.getErrorColor()));
                    else
                        setGlow(player, false);
                    return;
                }

            }
            printUsageMessage();
        } if (args.length < 3) {

            if (Bukkit.getOnlinePlayers().contains(args[0])) {

                Player target = Bukkit.getPlayer(args[0]);
                if (sender.hasPermission(getOtherPermission())) {
                    if(target.equals(sender))
                        printUsageMessage();
                    if(args.length == 1)
                        toggleGlow(target, sender);
                    else{
                        if(args[1].equalsIgnoreCase("on")) {
                            if (isGlowing(target))
                                chat.send(Chat.getErrorColor() + ConfigUtils.getString("tdrstudios.commands.glow.msgs.fail.already").replace("%Status%", Chat.getAccentColor() + "on" + Chat.getErrorColor()).replace("%Player%", Chat.getAccentColor() + target.getName() + Chat.getErrorColor()));
                            else
                                setGlow(target, true);

                        }else if(args[1].equalsIgnoreCase("off")) {
                            if (isGlowing(target))
                                chat.send(Chat.getErrorColor() + ConfigUtils.getString("tdrstudios.commands.glow.msgs.fail.already").replace("%Status%", Chat.getAccentColor() + "off" + Chat.getErrorColor()).replace("%Player%", Chat.getAccentColor() + target.getName() + Chat.getErrorColor()));
                            else
                                setGlow(target, false);

                        }else{
                            printUsageMessage();
                        }
                    }

                } else {
                    Chat.sendFast(sender, new LackingPermissionMessage(getOtherPermission()));
                }

            } else {
                if (args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("off")) {
                    Chat.sendFast(sender, new OnlySenderMessage(SenderType.PLAYER));
                } else {
                    chat.send(Chat.getErrorColor() + "The Player " + Chat.getAccentColor() + args[0] + Chat.getErrorColor() + " is not online!");
                }
            }


        }else
            printUsageMessage();
    }


    private boolean isGlowing(Player player) {
        return player.hasPotionEffect(PotionEffectType.GLOWING);
    }

    private String getToggledMessage(boolean status) {
        String s = "on";
        if (!status)
            s = "off";
        return Chat.getChatColor() + ConfigUtils.getString("tdrstudios.commands.glow.msgs.toggled").replace(
                "%Status%", Chat.getAccentColor() + s + Chat.getChatColor());
    }

    private String getToggledMessage(boolean status, CommandSender initiator) {
        String s = "on";
        if (!status)
            s = "off";
        return Chat.getChatColor() + ConfigUtils.getString("tdrstudios.commands.glow.msgs.toggled-byPlayer").replace(
                "%Status%", Chat.getAccentColor() + s + Chat.getChatColor()).replace(
                "%Player%", Chat.getAccentColor() + initiator.getName() + Chat.getChatColor()
        );
    }

    private void toggleGlow(Player player) {
        if (isGlowing(player)) {
            player.removePotionEffect(GLOW_EFFECT.getType());
            getPlayerChat().send(player, getToggledMessage(false));
        } else {
            GLOW_EFFECT.apply(player);
            getPlayerChat().send(player, getToggledMessage(true));
        }
        SoundUtils.playSound(player, TOGGLE_SOUND);
    }

    private void toggleGlow(Player player, CommandSender initiator) {
        if (isGlowing(player)) {
            player.removePotionEffect(GLOW_EFFECT.getType());
            getPlayerChat().send(player, getToggledMessage(false, initiator));
        } else {
            GLOW_EFFECT.apply(player);
            getPlayerChat().send(player, getToggledMessage(true, initiator));
        }
        SoundUtils.playSound(player, TOGGLE_SOUND, 2);
    }
    private void setGlow(Player player, boolean b, CommandSender initiator) {
        if (!b) {
            player.removePotionEffect(GLOW_EFFECT.getType());
            getPlayerChat().send(player, getToggledMessage(false, initiator));
        } else {
            GLOW_EFFECT.apply(player);
            getPlayerChat().send(player, getToggledMessage(true, initiator));
        }
        SoundUtils.playSound(player, TOGGLE_SOUND, 2);
    }
    private void setGlow(Player player, boolean b) {
        if (!b) {
            player.removePotionEffect(GLOW_EFFECT.getType());
            getPlayerChat().send(player, getToggledMessage(false));
        } else {
            GLOW_EFFECT.apply(player);
            getPlayerChat().send(player, getToggledMessage(true));
        }
        SoundUtils.playSound(player, TOGGLE_SOUND);
    }
}
