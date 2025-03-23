package me.zombie_striker.qg.guns.chargers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


import me.zombie_striker.qg.QAMain;
import me.zombie_striker.qg.guns.Gun;
import me.zombie_striker.qg.guns.utils.WeaponSounds;

public class BreakactionCharger implements ChargingHandler {

	List<UUID> timeC = new ArrayList<>();
	List<UUID> timeR = new ArrayList<>();

	public BreakactionCharger() {
		ChargingManager.add(this);
	}
	
	@Override
	public boolean isCharging(Player player) {
		return timeC.contains(player.getUniqueId());
	}


	@Override
	public boolean shoot(Gun g, final Player player, ItemStack stack) {
		timeC.add(player.getUniqueId());

		QAMain.myBukkit.runTaskLater(player, null, null, () -> {
			player.getWorld().playSound(player.getLocation(), g.getChargingSound(), 1, 1f);
		},10);

//		new BukkitRunnable() {
//			@Override
//			public void run() {
//				player.getWorld().playSound(player.getLocation(), g.getChargingSound(), 1, 1f);
//			}
//		}.runTaskLater(QAMain.getInstance(), 10);

		QAMain.myBukkit.runTaskLater(player, null, null, () -> {
			player.getWorld().playSound(player.getLocation(), g.getChargingSound(), 1, 1f);
		},15);

//		new BukkitRunnable() {
//
//			@Override
//			public void run() {
//				player.getWorld().playSound(player.getLocation(), g.getChargingSound(), 1, 1f);
//			}
//		}.runTaskLater(QAMain.getInstance(), 15);

		QAMain.myBukkit.runTaskLater(player, null, null, () -> {
			timeC.remove(player.getUniqueId());
		},(long) (g.getDelayBetweenShotsInSeconds()*20));

//		new BukkitRunnable() {
//
//			@Override
//			public void run() {
//				timeC.remove(player.getUniqueId());
//			}
//		}.runTaskLater(QAMain.getInstance(), (long) (g.getDelayBetweenShotsInSeconds()*20));
		return true;
	}


	@Override
	public String getName() {
		return ChargingManager.BREAKACTION;
	}

	@Override
	public String getDefaultChargingSound() {
		return WeaponSounds.RELOAD_BULLET.getSoundName();
		//g.getChargingSound()
	}
}
