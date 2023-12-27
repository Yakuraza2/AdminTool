package org.won.staff.wonstaff;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class gamemode implements CommandExecutor {
    private static WonStaff main;
    public gamemode(WonStaff Creative) { main = Creative;}
    public static boolean hasCreativePerm(String pseudo){
        Player player = Bukkit.getPlayer(pseudo);
        UUID playerUUID = player.getUniqueId();
        File file = main.getFile("gamemode");
        YamlConfiguration gmList = YamlConfiguration.loadConfiguration(file);

        final String key = "creative."+ playerUUID + "";
        final ConfigurationSection configurationSection = gmList.getConfigurationSection(key);

        if (configurationSection != null) {
            System.out.println("[DEBUG] " + player.getName() + " est dans gamemode.yml. path: " + key);
            return true;
        } else {
            System.out.println("[DEBUG] " + player.getName() + " n'est pas dans gamemode.yml. path: " + key);
            return false;
        }
    }

    public static void creativeKick(String pseudo){
        Player player = Bukkit.getPlayer(pseudo);
        UUID playerUUID = player.getUniqueId();
        if(player.getGameMode() == GameMode.CREATIVE){
            while(player.isOnline()){
                System.out.println(ChatColor.RED + "[SECURITY BREACH]" + player.getName() + " EST EN CREATIVE MODE !  UUID: " + playerUUID);
                player.kickPlayer(ChatColor.RED + "Vous n'avez pas le droit d'être en Creatif !");
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        File file = main.getFile("gamemode");
        YamlConfiguration gmList = YamlConfiguration.loadConfiguration(file);
        UUID playerUUID = Bukkit.getPlayer(args[0]).getUniqueId();
        final String key = "creative."+playerUUID;
        final ConfigurationSection configurationSection = gmList.getConfigurationSection(key);


        System.out.println("[DEBUG] Joueur a ajouter : " + args[0] + "  UUID: " + playerUUID);

        if(configurationSection == null){
            gmList.set("creative."+playerUUID+".Pseudo", args[0]);
            gmList.set("creative."+playerUUID+".Adder", sender);

            System.out.println(sender + " a ajouté " + args[0] + " aux perms GM.  UUID:" + playerUUID);
            System.out.println("path: " + "creative."+playerUUID);
        }else{
            gmList.set("creative."+playerUUID, null);
            System.out.println(sender + " a supprimé " + args[0] + " aux perms GM.  UUID:" + playerUUID);
            System.out.println("path: " + "creative."+playerUUID);
            creativeKick(args[0]);
        }

        try {
            gmList.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}
