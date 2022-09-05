package com.firstproj.mcplugfirst.commands

import com.firstproj.mcplugfirst.modules.msg
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class heal: CommandExecutor {
    override fun onCommand(p0: CommandSender, p1: Command, label: String, p3: Array<out String>?): Boolean {
        if (p0 !is Player) {
            msg.send(p0, "Here")
            return true
        }

        if (p3 != null) {
            if (p3.size > 1) {
                val player: Player? = Bukkit.getPlayer(p3[0])

                if (player != null) {
                    if (p3[1].toInt() > 20) {
                        player.sendMessage("the heal amount can't be more than 20")
                        return true
                    }
                    player.health = p3[1].toDouble()
                }


            }
        }


        return true
    }
}