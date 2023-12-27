package org.won.staff.wonstaff;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChat implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {

        if(cmd.getName().equalsIgnoreCase("cm")){
            for(Player onlinePlayer : Bukkit.getOnlinePlayers()){
                if(onlinePlayer.hasPermission("chat.modo")){
                    onlinePlayer.sendMessage("§2 Ⓜ [§aChat Modo§2] " + sender.getName() + " : §a" + WonStaff.strBuilder(strings));
                }
            }
        }

        if(cmd.getName().equalsIgnoreCase("cad")){
            for(Player onlinePlayer : Bukkit.getOnlinePlayers()){
                if(onlinePlayer.hasPermission("chat.admin")){
                    onlinePlayer.sendMessage("§4§l Ⓐ [§c§lChat Admin§4§l] §r§c" + sender.getName() + " : §r§c" + WonStaff.strBuilder(strings));
                }
            }
        }

        return false;
    }
}
