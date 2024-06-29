package org.ezhik.cs2craft.commands;

import org.ezhik.cs2craft.CsGameplay;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuymenuCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!CsGameplay.sorting) {
            Player player = (Player) commandSender;
            if (CsGameplay.getcommand(player) == "ct") {
                if (player.getLocation().getX() <= 108 && player.getLocation().getX() >= 96 && player.getLocation().getZ() <= -84 && player.getLocation().getZ() >= -94) {
                    CsGameplay.showbuymenu(player);
                } else player.sendMessage("Вы не в зоне покупок");
            }
            if (CsGameplay.getcommand(player) == "t") {
                if (player.getLocation().getX() <= 160 && player.getLocation().getX() >= 155 && player.getLocation().getZ() <= -70 && player.getLocation().getZ() >= -78) {
                    CsGameplay.showbuymenu(player);
                } else player.sendMessage("Вы не в зоне покупок");
            }
        }

        return true;
    }

}
