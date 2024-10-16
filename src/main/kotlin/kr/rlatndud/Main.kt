package kr.rlatndud

import kr.rlatndud.sword.Hinokami_Kagura
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    companion object { lateinit var instance: Main }

    override fun onEnable() {
        logger.info("plugin enable")

        instance = this

        server.pluginManager.apply {
            registerEvents(Hinokami_Kagura(), this@Main)
        }

    }
}