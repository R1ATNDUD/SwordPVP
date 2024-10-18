package kr.rlatndud

import kr.rlatndud.sword.FireAbility_Sword
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    companion object { lateinit var instance: Main }

    override fun onEnable() {
        logger.info("plugin enable")

        instance = this

        server.pluginManager.apply {
            registerEvents(FireAbility_Sword(), this@Main)
        }

    }
}