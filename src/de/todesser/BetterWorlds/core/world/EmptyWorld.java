package de.todesser.BetterWorlds.core.world;

import org.bukkit.*;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

public class EmptyWorld implements IWorldCreator {

    ChunkGenerator chunk = new EmptyChunkGenerator();

    @Override
    public void create(String name) {
        WorldCreator world = new WorldCreator(name);
        world.generator(new EmptyChunkGenerator());
        world.generateStructures(false);
        World w = world.createWorld();

        w.setSpawnLocation(0, 100, 0);
        new Location(w, 0, 99, 0).getBlock().setType(Material.BEDROCK);

        w.save();
    }


    /*
        WorldCreator world = new WorldCreator(args[0]);
        world.generator(new EmptyChunkGenerator());
        world.generateStructures(false);

        World w = Bukkit.createWorld(world);
        w.setSpawnLocation(0, 100, 0);
        new Location(w, 0, 99, 0).getBlock().setType(Material.BEDROCK);

     */


}
