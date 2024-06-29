package org.ezhik.cs2craft.commands;

import org.ezhik.cs2craft.CsGameplay;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SortoffCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        CsGameplay.stopsort();
        return true;
    }
}
