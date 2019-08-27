package de.todesser.BetterWorlds.resource_bundle;

import java.util.Locale;

public enum Language {
    US_ENGLISH("en", "US", "American English"), //is default
    DE_GERMAN("de", "DE", "Deutsch");


    private final String LANGUAGE_ABBREVATION;
    private final String COUNTRY_ABBREVATION;
    private final String LANGUAGE;

    Language(String langAbbrev, String countryAbbrev, String lang) {
        LANGUAGE_ABBREVATION = langAbbrev;
        COUNTRY_ABBREVATION = countryAbbrev;

        LANGUAGE = lang;
    }

    public static Language localeToLanguage(Locale local) {
        for (Language lang : Language.values()) {
            if(local.getCountry().equals(lang.COUNTRY_ABBREVATION)) {
                if(local.getLanguage().equals(new Locale(lang.LANGUAGE_ABBREVATION).getLanguage())) {
                    return lang;
                }
            }
        }
        return US_ENGLISH;
    }

    public Locale asLocale() {
        return new Locale(LANGUAGE_ABBREVATION, COUNTRY_ABBREVATION);
    }

    @Override
    public String toString() {
        return LANGUAGE;
    }

}
