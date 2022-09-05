package com.firstproj.mcplugfirst

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.title.Title
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import java.util.Collections

class EventListener : Listener {

    private val mainTitle: Component = Component.text("M&M 1234", NamedTextColor.WHITE)
    private val subTitle: Component = Component.text("By Fishje", NamedTextColor.GRAY)

    @EventHandler
    fun onJoinGame(e: PlayerJoinEvent) {

        e.player.teleport(Location(e.player.world, -223.0, 92.0, -575.0))
        e.player.showTitle(Title.title(mainTitle, subTitle))

        e.player.inventory.clear()
        e.player.foodLevel = 18

    }

    @EventHandler
    fun onGameStartCLicked(e: PlayerInteractEvent) {
        val player = e.player
        val action = e.action


        if (!action.isRightClick || e.clickedBlock?.type !== Material.STONE_BUTTON) return
        val players: List<Player> = ArrayList(e.player.server.onlinePlayers)

        if (players.size < 1) {
            player.sendMessage("there are not enough people to start the game..!"); return
        }
        var roles: MutableList<String> = mutableListOf("Murderer", "detective")


        players.forEach {

            it.teleport(Location(e.player.world, -209.0, 78.0, -589.0))

            var rand = roles.random()
            if (rand == "Murderer") {

                val mainTitle: Component = Component.text("You are the murderer", NamedTextColor.RED)
                val subTitle: Component = Component.text("Grab your drinks and kill people", NamedTextColor.GRAY)

                it.showTitle(Title.title(mainTitle, subTitle))

                val Sword: ItemStack = ItemStack(Material.IRON_SWORD, 1); Sword.addEnchantment(Enchantment.DAMAGE_ALL, 5)
                val Bow: ItemStack = ItemStack(Material.BOW, 1); Bow.addEnchantment(
                    Enchantment.ARROW_DAMAGE,
                    5
                ); Bow.addEnchantment(Enchantment.ARROW_FIRE, 1)


                it.inventory.addItem(ItemStack(Material.ARROW, 1))
                it.inventory.addItem(Sword)
                it.inventory.addItem(Bow)
                roles.remove("Murderer")
                rand = roles.random()

            } else if (rand == "detective") {


                val mainTitle: Component = Component.text("You are the detective", NamedTextColor.BLUE)
                val subTitle: Component = Component.text("kill the murderer", NamedTextColor.GRAY)

                it.showTitle(Title.title(mainTitle, subTitle))

                val Sword: ItemStack = ItemStack(Material.IRON_SWORD, 1); Sword.addEnchantment(Enchantment.DAMAGE_ALL, 5)
                val Bow: ItemStack = ItemStack(Material.BOW, 1); Bow.addEnchantment(
                    Enchantment.ARROW_DAMAGE,
                    5
                ); Bow.addEnchantment(Enchantment.ARROW_FIRE, 1)


                it.inventory.addItem(ItemStack(Material.ARROW, 1))
                it.inventory.addItem(Sword)
                it.inventory.addItem(Bow)
                roles.remove("detective")
                rand = roles.random()
            } else if (rand == "innocent") {
                val mainTitle: Component = Component.text("You are Innocent", NamedTextColor.GREEN)
                val subTitle: Component = Component.text("Stay alive and be careful..", NamedTextColor.GRAY)

                it.showTitle(Title.title(mainTitle, subTitle))
            }
        }
    }

    @EventHandler
    fun onDie(e: PlayerDeathEvent) {

        if (e.player.inventory.contains(ItemStack(Material.IRON_SWORD))) {
            ArrayList(e.player.server.onlinePlayers).forEach {
                it.showTitle(
                    Title.title(
                        Component.text("The Murderer have died", NamedTextColor.GREEN),
                        Component.text("teleporting everyone to the lobby", NamedTextColor.BLUE)
                    )
                )
            }
        }
        e.player.teleport(Location(e.player.world, -223.0, 92.0, -575.0))
        e.player.inventory.clear()
    }
}

