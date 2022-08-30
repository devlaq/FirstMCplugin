package com.firstproj.mcplugfirst

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent

class HelloWorld: Listener {
    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) {
        print("placed ${event.block.type}")
    }
}