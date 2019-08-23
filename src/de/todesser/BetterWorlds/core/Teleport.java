package de.todesser.BetterWorlds.core;

import de.todesser.BetterWorlds.core.world.World;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

public class Teleport {

    public static class Point {

        int x, y, z;

        public Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static boolean teleport(Player player, String world) {
        return teleport(player, world, null);
    }


    public static boolean teleport(Player player, String world, Point point) {
        if(!World.doesWorldExsist(world)) {
            return false;
        }

        if(point == null) {
            player.teleport(Bukkit.createWorld(new WorldCreator(world)).getSpawnLocation());
        } else {
            player.teleport(new Location(Bukkit.createWorld(new WorldCreator(world)), point.x, point.y, point.z));
        }

        return true;
    }

}
