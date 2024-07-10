package com.of21.pajoinleave;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PAJoinLeave extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Save the default config if it doesn't exist
        this.saveDefaultConfig();
        // Register events
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        FileConfiguration config = this.getConfig();
        String joinMessage;
        if (!event.getPlayer().hasPlayedBefore()) {
            joinMessage = config.getString("first-join-message", "&eWelcome &6%player% &eto the server for the first time!");
        } else {
            joinMessage = config.getString("join-message", "&6%player% &ejoined the server.");
        }
        joinMessage = ChatColor.translateAlternateColorCodes('&', joinMessage.replace("%player%", event.getPlayer().getName()));
        event.setJoinMessage(joinMessage);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        FileConfiguration config = this.getConfig();
        String quitMessage = config.getString("leave-message", "&6%player% &eleft the server.");
        quitMessage = ChatColor.translateAlternateColorCodes('&', quitMessage.replace("%player%", event.getPlayer().getName()));
        event.setQuitMessage(quitMessage);
    }
}
