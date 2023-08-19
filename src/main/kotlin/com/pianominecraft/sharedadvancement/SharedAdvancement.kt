package com.pianominecraft.sharedadvancement

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class SharedAdvancement : JavaPlugin() {
    override fun onEnable() {
        server.scheduler.scheduleSyncRepeatingTask(this, {
            server.onlinePlayers.forEach { p ->
                val iterator = Bukkit.advancementIterator()
                while (iterator.hasNext()) {
                    val advancement = iterator.next()
                    if (p.getAdvancementProgress(advancement).awardedCriteria.isNotEmpty()) {
                        server.onlinePlayers.forEach { t ->
                            p.getAdvancementProgress(advancement).awardedCriteria.forEach { c ->
                                t.getAdvancementProgress(advancement).awardCriteria(c!!)
                            }
                        }
                    }
                }
            }
        }, 0L, 1L)
    }
}