package me.zombie_striker.qg.guns.reloaders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.zombie_striker.qg.api.QualityArmory;
import me.zombie_striker.qg.guns.utils.WeaponSounds;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import me.zombie_striker.qg.QAMain;
import me.zombie_striker.qg.guns.Gun;

public class PumpactionReloader implements ReloadingHandler {

	List<UUID> timeR = new ArrayList<>();

	public PumpactionReloader() {
		ReloadingManager.add(this);
	}

	@Override
	public boolean isReloading(Player player) {
		return timeR.contains(player.getUniqueId());
	}

	@Override
	public double reload(final Player player, Gun g, int amountReloading) {
		timeR.add(player.getUniqueId());
		double time = (g.getReloadTime()) / g.getMaxBullets();
		double time2 = time * amountReloading;
		for (int i = 0; i < amountReloading; i++) {
			final boolean k = (i + 1 == amountReloading);
			final int finalI = i;

			int temp = player.getInventory().getHeldItemSlot();

			QAMain.myBukkit.runTaskLater(player, null, null, () -> {

					if(player.getInventory().getHeldItemSlot()!=temp)
						return;
					QualityArmory.sendHotbarGunAmmoCount(player, g, player.getInventory().getItemInMainHand(), true, g.getMaxBullets()-amountReloading+finalI, g.getMaxBullets());

					try {
						if (k) {
							player.getWorld().playSound(player.getLocation(), g.getReloadingSound(), 5, 4f);
							player.getWorld().playSound(player.getLocation(), WeaponSounds.RELOAD_SHOTGUN.getSoundName(), 8, 1.4f);
						}
						player.getWorld().playSound(player.getLocation(), g.getReloadingSound(), 5, 4f);
					} catch (Error e) {
						try {
							if (k) {
								player.getWorld().playSound(player.getLocation(), g.getReloadingSound(), 5,
										4f);
								player.getWorld().playSound(player.getLocation(), Sound.valueOf("DIG_SAND"), 8, 1.4f);
							}
							player.getWorld().playSound(player.getLocation(), g.getReloadingSound(), 5, 4f);
						} catch (Error | Exception e43) {
						}
					}
			},(int) (time * i * 20));

//			new BukkitRunnable() {
//				int temp = player.getInventory().getHeldItemSlot();
//
//				@Override
//				public void run() {
//					if(player.getInventory().getHeldItemSlot()!=temp)
//						return;
//					QualityArmory.sendHotbarGunAmmoCount(player, g, player.getInventory().getItemInMainHand(), true, g.getMaxBullets()-amountReloading+finalI, g.getMaxBullets());
//
//					try {
//						if (k) {
//							player.getWorld().playSound(player.getLocation(), g.getReloadingSound(), 5, 4f);
//							player.getWorld().playSound(player.getLocation(), WeaponSounds.RELOAD_SHOTGUN.getSoundName(), 8, 1.4f);
//						}
//						player.getWorld().playSound(player.getLocation(), g.getReloadingSound(), 5, 4f);
//					} catch (Error e) {
//						try {
//							if (k) {
//								player.getWorld().playSound(player.getLocation(), g.getReloadingSound(), 5,
//										4f);
//								player.getWorld().playSound(player.getLocation(), Sound.valueOf("DIG_SAND"), 8, 1.4f);
//							}
//							player.getWorld().playSound(player.getLocation(), g.getReloadingSound(), 5, 4f);
//						} catch (Error | Exception e43) {
//						}
//					}
//				}
//			}.runTaskLater(QAMain.getInstance(), (int) (time * i * 20));
		}

		QAMain.myBukkit.runTaskLater(player, null, null, () -> 	timeR.remove(player.getUniqueId()),(int) (time2 * 20) + 5);

//		new BukkitRunnable() {
//			@Override
//			public void run() {
//				timeR.remove(player.getUniqueId());
//
//			}
//		}.runTaskLater(QAMain.getInstance(), (int) (time2 * 20) + 5);
		return time2;
	}

	@Override
	public String getName() {

		return ReloadingManager.PUMP_ACTION_RELOAD;
	}

	@Override
	public String getDefaultReloadingSound() {
		return WeaponSounds.RELOAD_SHELL.getSoundName();
	}
}
