package com.github.styuchan1005.blood.blood;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Blood extends JavaPlugin implements Listener {
	private static Blood instance;

	@Override
	public void onEnable() {
		instance = this;
		this.getServer().getPluginManager().registerEvents(this, this);
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
