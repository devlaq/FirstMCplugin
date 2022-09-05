package com.firstproj.mcplugfirst

import com.firstproj.mcplugfirst.commands.heal
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.plugin.java.JavaPlugin

class MCplugFirst : JavaPlugin() {
    override fun onEnable() {

        Companion.instance = this
        // Plugin startup logic
        logger.info("Hello World! 222")
        Bukkit.getPluginManager().registerEvents(EventListener(), this)
        getCommand("setHealth")?.setExecutor(heal())
    }


    override fun onDisable() {
        // Plugin shutdown logic
    }

    companion object {
        private var instance: MCplugFirst? = null
        fun getInstance() : MCplugFirst? = Companion.instance
    }
}

