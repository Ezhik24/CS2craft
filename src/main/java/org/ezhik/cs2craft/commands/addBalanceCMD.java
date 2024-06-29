package org.ezhik.cs2craft.commands;

import org.ezhik.cs2craft.CsGameplay;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class addBalanceCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        int ballance = Integer.parseInt(strings[0]);
        CsGameplay.addbalance(strings[1], ballance);
        CsGameplay.addbalance(strings[2], ballance);
        CsGameplay.addbalance(strings[3], ballance);
        CsGameplay.addbalance(strings[4], ballance);
        CsGameplay.addbalance(strings[5], ballance);
        CsGameplay.addbalance(strings[6], ballance);
        CsGameplay.addbalance(strings[7], ballance);
        CsGameplay.addbalance(strings[8], ballance);
        CsGameplay.addbalance(strings[9], ballance);
        return true;
    }
}
