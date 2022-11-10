package me.lacys.tntthrow

import me.lacys.tntthrow.listener.TntListener
import org.bukkit.plugin.java.JavaPlugin

class TNTThrow : JavaPlugin() {
    override fun onEnable() {
        saveDefaultConfig()
        server.pluginManager.registerEvents(TntListener(config.getInt("fuseTicks"), config.getInt("multiply"), config.getInt("cooldown")), this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}