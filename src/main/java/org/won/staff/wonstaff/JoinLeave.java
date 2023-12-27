package org.won.staff.wonstaff;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeave implements Listener {
//&7[&a+&7] {USERNAME} (&o{ONLINE}/100&7) &7[&c-&7] {USERNAME} (&o{ONLINE}/100&7)
    private WonStaff main;
    public JoinLeave(WonStaff joinLeave) { this.main = joinLeave;}

    @EventHandler
    public void JoinEvent(PlayerJoinEvent e){
        Player player = e.getPlayer();
        int onlinePlayers = main.getServer().getOnlinePlayers().size();
        if(!gamemode.hasCreativePerm(player.getName())){
            gamemode.creativeKick(player.getName());
        }
        e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.GRAY + player.getName() + " (" + onlinePlayers + "/" + main.getServer().getMaxPlayers() + ")");
    }
    @EventHandler
    public void QuitEvent(PlayerQuitEvent e){
        Player player = e.getPlayer();
        int onlinePlayers = main.getServer().getOnlinePlayers().size() - 1;

        e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.GRAY + player.getName() + "(" + onlinePlayers + "/" + main.getServer().getMaxPlayers() + ")");
    }
}
