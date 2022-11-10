package me.lacys.tntthrow.listener

import org.bukkit.Material
import org.bukkit.entity.TNTPrimed
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class TntListener(private val fuzeTea: Int, private val multiply: Int, private val cooldown: Int) : Listener {

    @EventHandler
    fun onTnt(event: PlayerInteractEvent) {
        if (event.action != Action.RIGHT_CLICK_AIR) return
        if (event.item == null) return
        if (event.item!!.type != Material.TNT) return

        if (event.player.getCooldown(Material.TNT) != 0) return

        val tnt = event.player.world.spawn(event.player.location, TNTPrimed::class.java)
        tnt.setFuzeTea(fuzeTea)
        tnt.velocity = event.player.location.direction.multiply(multiply)

        val item = event.player.itemInHand

        if (item.amount == 1) {
            event.player.inventory.remove(item);
        } else {
            item.amount = item.amount - 1;
        }

        event.player.setCooldown(Material.TNT, cooldown)
    }
}

fun TNTPrimed.setFuzeTea(fuzeTea: Int) {
    this.fuseTicks = fuzeTea
}