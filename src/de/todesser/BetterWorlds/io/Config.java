package de.todesser.BetterWorlds.io;

import de.todesser.BetterWorlds.main.Main;

import java.util.List;

public class Config {

    public static void set(String field, String content) {
        List<String> list = Main.getPlugin().getConfig().getStringList(field);
        list.add(content);
        Main.getPlugin().getConfig().set(field, list);
        Main.getPlugin().saveConfig();
    }

    public static void remove(String field, String content) {
        List<String> list = Main.getPlugin().getConfig().getStringList(field);
        list.remove(content);
        Main.getPlugin().getConfig().set(field, list);
        Main.getPlugin().saveConfig();
    }

    public static List<String> get(String field) {
        return Main.getPlugin().getConfig().getStringList(field);
    }


}
