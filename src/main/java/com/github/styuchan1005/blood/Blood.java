package com.github.styuchan1005.blood;

import org.bukkit.Bukkit;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Blood extends JavaPlugin implements Listener {
	private static Blood instance;
	private static WorldBorder worldBorder;

	@Override
	public void onEnable() {
		instance = this;
		this.getServer().getPluginManager().registerEvents(this, this);
		worldBorder = Bukkit.getWorld("world").getWorldBorder();
		Bukkit.getScheduler().runTaskTimer(instance, () -> {
			if (worldBorder.getSize() -2 > 0) worldBorder.setSize(worldBorder.getSize() + 2);
		}, 12000, 12000);
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		worldBorder.setSize(worldBorder.getSize() - 2);
	}

	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		Entity entity = event.getEntity();
		if (!(entity instanceof Player)) return;
		Player player = (Player) entity;
		WorldBorderAPI.send(player);
		Bukkit.getScheduler().runTaskLater(instance, new BukkitRunnable() {
			@Override
			public void run() {
				WorldBorderAPI.remove(player);
			}
		}, 40L);
	}

}
