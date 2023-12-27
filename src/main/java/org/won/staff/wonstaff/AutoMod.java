package org.won.staff.wonstaff;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;

public class AutoMod implements Listener {
    Map<String, String> lastMessage = new HashMap<>();

    private final WonStaff main;
    public AutoMod(WonStaff mod) {
        this.main = mod;
    }

    @EventHandler
    public void onGamemodeChange(PlayerGameModeChangeEvent e) {
        if (!gamemode.hasCreativePerm(e.getPlayer().getName())) {
            gamemode.creativeKick(e.getPlayer().getName());
        }
    }

    @EventHandler
    public void OnMoveEvent(PlayerMoveEvent e) {
        if(!gamemode.hasCreativePerm(e.getPlayer().getName())){
            gamemode.creativeKick(e.getPlayer().getName());
        }
    }
    @EventHandler
    public void OnChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String texte = e.getMessage().toLowerCase();
        boolean cancelled = e.isCancelled();

        if(!cancelled){

            for(String langage : main.getConfig().getStringList("langage")){
                if(texte.contains(langage)){
                    e.setCancelled(true);
                    System.out.println(e.getPlayer().getName() + " has tried to say " + e.getMessage());
                    p.sendMessage("§cMot interdit: " + e.getMessage());
                    return;
                }
            }

            if(!e.getMessage().startsWith("/")){
                if(!p.isOp()){
                    if(e.getMessage().equalsIgnoreCase(lastMessage.get(p.getName()))){
                        e.setCancelled(true);
                        System.out.println(p.getDisplayName() + " has tried to SPAM : " + lastMessage.get(p.getName()));
                        return;
                    } else {
                        lastMessage.put(p.getName(), e.getMessage());
                    }

                    int nbr_majs = 0;
                    for(int i = 0; i<e.getMessage().length(); i++){
                        char ch = e.getMessage().charAt(i);
                        if(Character.isUpperCase(ch)) nbr_majs++;
                    }
                    if (nbr_majs>4 && (float)nbr_majs/e.getMessage().length()>0.40){
                        System.out.println(p.getName() + " Has tried to MAJ : " +e.getMessage());
                        e.setMessage(e.getMessage().toLowerCase());
                    }
                }
                for(String word : e.getMessage().split(" ")){
                    Player target = Bukkit.getPlayer(word);
                    if(target != null && target.getName().equalsIgnoreCase(word)){
                        System.out.println(p.getName() + " HAS PING " + target.getName());
                        target.sendMessage("§a ● " + p.getName() + " vous a ping dans le chat !");
                        //target.playSound(target.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 1.0f, 1.0f);
                        e.setMessage(e.getMessage().replace(target.getName(), "§e" + target.getName() + "§r"));
                    }
                }
            } else { gamemode.hasCreativePerm(p.getName());}
        }
    }
}
