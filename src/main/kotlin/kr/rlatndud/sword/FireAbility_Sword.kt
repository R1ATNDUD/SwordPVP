package kr.rlatndud.sword

import kr.rlatndud.Main.Companion.instance
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import kotlin.math.cos
import kotlin.math.sin

class FireAbility_Sword : Listener {

    @EventHandler
    fun onClick(e: PlayerInteractEvent) {
        val p = e.player
        val a = e.action
        val center = p.location
        val world = p.world
        val radius = 3.0  // 원의 반지름
        val particleCount = 100
        val player_direction = p.location.direction

        if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) {

            if (e.item?.type == null) return

            if (e.item?.type == Material.GOLDEN_SWORD) {

                p.velocity = player_direction.multiply(2.0)

                for (i in 0 until particleCount) {
                    val angle = 2 * Math.PI * i / particleCount  // 각도 계산

                    val x = radius * cos(angle)

                    val z = radius * sin(angle)

                    val particleLocation = center.clone().add(x, 0.0, z)  // 플레이어 위치에서 X, Z 축으로 이동

                    world.spawnParticle(Particle.FLAME, particleLocation, 7, 0.0, 0.0, 0.0, 0.0)  // 파티클 소환
                }

                p.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 60, 5, false, false))

                p.addPotionEffect(PotionEffect(PotionEffectType.RESISTANCE, 40, 5, false, false))

                Bukkit.getScheduler().runTaskLater(instance,  { _->

                        world.spawnParticle(Particle.FLAME, p.location, 15, 0.0, 0.0, 0.0)

                        p.sendActionBar(Component.text("[스킬 사용중...]").color(NamedTextColor.DARK_RED))

                },60L)

            }
        }


    }
}