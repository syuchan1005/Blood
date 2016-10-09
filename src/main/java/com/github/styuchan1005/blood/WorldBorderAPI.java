package com.github.styuchan1005.blood;

import net.minecraft.server.v1_10_R1.PacketPlayOutWorldBorder;
import net.minecraft.server.v1_10_R1.WorldBorder;
import org.bukkit.craftbukkit.v1_10_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class WorldBorderAPI {

	public static void send(Player p, int percentage) {
		if (percentage > 100) percentage = 100;
		percentage = Math.round((float)(100 - percentage /1));
		int dist = -146 * percentage + 20000;
		WorldBorder border = new WorldBorder();
		border.setCenter(p.getLocation().getX(), p.getLocation().getZ());
		border.setWarningDistance(dist);
		border.setWarningTime(15);
		border.transitionSizeBetween(10000, 10000, 0L);

		PacketPlayOutWorldBorder packet = new PacketPlayOutWorldBorder(border, PacketPlayOutWorldBorder.EnumWorldBorderAction.INITIALIZE);
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
	}

	public static void send(Player p) { send(p, 100); }

	public static void remove(Player p) {
		WorldBorder border = (((CraftWorld)p.getWorld()).getHandle()).getWorldBorder();

		PacketPlayOutWorldBorder packet = new PacketPlayOutWorldBorder(border, PacketPlayOutWorldBorder.EnumWorldBorderAction.INITIALIZE);
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
	}
}