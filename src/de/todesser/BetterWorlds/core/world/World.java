package de.todesser.BetterWorlds.core.world;

import de.todesser.BetterWorlds.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class World {

    public static final String PREFIX = "bw_";
    public static final String STD_PREFIX = "std_";

    public World getWorld() {
        return this;
    }

    public static boolean load(String name) {
        if(!doesWorldExsist(name)) {
            return false;
        }
        Bukkit.createWorld(new WorldCreator(name));
        return true;
    }

    public static boolean create(String name, WorldType worldType) {
        if(doesWorldExsist(name)) {
            return false;
        }

        if(worldType == null) {
           return false;
        }

        worldType.getWorldCreator().create(name);
        return true;
    }

    public static boolean create(WorldCreator worldCreator) {
        if(doesWorldExsist(worldCreator.name())) {
            return false;
        }

        Bukkit.createWorld(worldCreator);
        return true;
    }

    public static boolean delete(String name) {
        Bukkit.unloadWorld(name, false);

        File f = new File(Main.getPlugin().getServer().getWorldContainer().getPath() + "\\" + name);

        if(!f.exists()) {
            return false;
        }

        try {
            FileUtils.deleteDirectory(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static boolean doesWorldExsist(String name) {
        return new File(Main.getPlugin().getServer().getWorldContainer().getPath() + "\\" + name).exists();
    }

}
