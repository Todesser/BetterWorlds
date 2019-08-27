package de.todesser.BetterWorlds.resource_bundle;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageLoader {

    private static String resourceBundleLocation;
    private static ResourceBundle resourceBundle;

    static {

        resourceBundleLocation = "de/todesser/BetterWorlds/resource_bundle/lang";
        resourceBundle = ResourceBundle.getBundle(resourceBundleLocation, Locale.getDefault());

    }

    public static String get(String key) {
        return resourceBundle.getString(key);
    }

    public static String get(String identifier, String key) {
        return get(identifier) + " " + get(key);
    }

    public static String get(String key, Object... args) {
        if(args == null) throw new NullPointerException();
        return MessageFormat.format(get(key), args);
    }

    public static String get(String identifier, String key, Object... args) {
        return get(identifier) + " " + get(key, args);
    }

    public static void setLanguage(Language lang) {
        Locale.setDefault(lang.asLocale());
        resourceBundle = ResourceBundle.getBundle(resourceBundleLocation);
    }

    public static Language getCurrentLanguage() {
        return Language.localeToLanguage(Locale.getDefault());
    }

}
