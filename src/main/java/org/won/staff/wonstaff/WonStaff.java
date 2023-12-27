package org.won.staff.wonstaff;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class WonStaff extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        System.out.println("[ModerationPlugin] ON");
        getCommand("jp").setExecutor(new CommandBroadcasts());
        getCommand("js").setExecutor(new CommandBroadcasts());
        getCommand("discord").setExecutor(new CommandBroadcasts());
        getCommand("site").setExecutor(new CommandBroadcasts());
        getCommand("event").setExecutor(new CommandBroadcasts());
        getCommand("cm").setExecutor(new StaffChat());
        getCommand("cad").setExecutor(new StaffChat());
        getCommand("gmperm").setExecutor(new gamemode(this));

        createFile("gamemode");

        registerEvents();
    }

    public void registerEvents(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new AutoMod(this), this);
        pm.registerEvents(new JoinLeave(this), this);
    }
    @Override
    public void onDisable() {System.out.println("[ModerationPlugin] OFF");}

    private void createFile(String fileName){
        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
            System.out.println("[FILES] Le dossier " + getDataFolder().getName() + " n'existe pas.");
            System.out.println("[FILES] Création du dossier " + getDataFolder().getName());
        }

        File file = new File(getDataFolder(), fileName + ".yml");

        if(!file.exists()){
            System.out.println("[FILES] Le fichier " + fileName + ".yml n'existe pas.");
            try {
                file.createNewFile();
                System.out.println("[FILES] Création du fichier " + fileName + ".yml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public File getFile(String fileName){return new File(getDataFolder(), fileName + ".yml");}

    public static String strBuilder(String[] args){
        String message = "";
        for(String part : args) {
            message = part + " ";
        }
        return message.replace("&", "§");
    }
}
