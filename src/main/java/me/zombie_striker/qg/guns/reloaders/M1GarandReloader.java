package me.zombie_striker.qg.guns.reloaders;

import me.zombie_striker.qg.QAMain;
import me.zombie_striker.qg.guns.Gun;
import me.zombie_striker.qg.guns.utils.WeaponSounds;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class M1GarandReloader implements ReloadingHandler{


	public M1GarandReloader() {
		ReloadingManager.add(this);
	}

	List<UUID> timeR = new ArrayList<>();
	@Override
	public boolean isReloading(Player player) {
		return timeR.contains(player.getUniqueId());
	}

	@Override
	public double reload(Player player, Gun g, int amountReloading) {
		timeR.add(player.getUniqueId());
		player.getWorld().playSound(player.getLocation(), WeaponSounds.RELOAD_CLICK.getSoundName(), 1, 1f);

		QAMain.myBukkit.runTaskLater(player, null, null, () -> {
			player.getWorld().playSound(player.getLocation(), g.getReloadingSound(), 1, 1f);
			timeR.remove(player.getUniqueId());
		},Math.max((int) ((g.getReloadTime()* 20.0) - 10.0),10));

//			new BukkitRunnable() {
//				@Override
//				public void run() {
//						player.getWorld().playSound(player.getLocation(), g.getReloadingSound(), 1, 1f);
//						timeR.remove(player.getUniqueId());
//				}
//			}.runTaskLater(QAMain.getInstance(), Math.max((int) ((g.getReloadTime()* 20.0) - 10.0),10));
		return g.getReloadTime();
	}

	@Override
	public String getName() {
		return ReloadingManager.M1GARAND_RELOAD;
	}

	@Override
	public String getDefaultReloadingSound() {
		return WeaponSounds.RELOAD_SLIDE.getSoundName();
	}
}
