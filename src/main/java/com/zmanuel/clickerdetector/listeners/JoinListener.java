package com.zmanuel.clickerdetector.listeners;

import com.zmanuel.clickerdetector.Main;
import com.zmanuel.clickerdetector.player.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements Listener {

    public JoinListener() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        new PlayerData(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Main.getInstance().getPlayerManager().getPlayers().remove(Main.getInstance().getPlayerManager().byPlayer(e.getPlayer()));
    }

}
