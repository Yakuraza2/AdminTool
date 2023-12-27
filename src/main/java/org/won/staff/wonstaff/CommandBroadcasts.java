package org.won.staff.wonstaff;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBroadcasts implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(cmd.getName().equalsIgnoreCase("js")){
            if(args.length == 0){
                sender.sendMessage("Usage: /js <message>");

            } else if (args.length >= 1) {
                Bukkit.broadcastMessage(WonStaff.strBuilder(args));
            }
        }

        if(cmd.getName().equalsIgnoreCase("jp")){
            if(args.length == 0){
                sender.sendMessage("Usage: /jp <player>");

            } else if (args.length >= 1) {
                Player target = Bukkit.getPlayer(args[0]);

                if(target != null){
                    args[0] = "";

                    target.sendMessage(WonStaff.strBuilder(args));
                }else{sender.sendMessage("Le joueur est déconnecté");}
            }
        }

        if(cmd.getName().equalsIgnoreCase("discord")){
            Bukkit.broadcastMessage("§1◆ §9Discord§1 ◆ §9Lien du discord de WorldOfNations : §o§nhttps://discord.gg/a66pbHpYkA");
        }

        if(cmd.getName().equalsIgnoreCase("site")){
            Bukkit.broadcastMessage("§6◆ §eSite§6 ◆ §eLien du site de WorldOfNations : §o§nhttps://worldofnations.fr");
        }

        if(cmd.getName().equalsIgnoreCase("event")){
            Bukkit.broadcastMessage("§e✦§6 Event §e► §6" + WonStaff.strBuilder(args));
        }

        return false;
    }
}
