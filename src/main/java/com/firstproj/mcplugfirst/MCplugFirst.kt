package com.firstproj.mcplugfirst

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class MCplugFirst : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        logger.info("Hello World!")
        Bukkit.getPluginManager().registerEvents(HelloWorld(), this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}

