package com.hexenwerk.tutorial.javafx.crm;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

public class ResourceBundleUtil {

    public static ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle("Bundle", JavaFxRunner.getLOCALE());
    }

    public static String getValue(String key) {
        return getResourceBundle().getString(key);
    }

    /**
     * might be useful for listing of supported languages in UI
     *
     * @return list of languages supported by Bundle_*.properties file
     */
    public static Set<Locale> getSupportedLanguages() {
        return Arrays.stream(Locale.getAvailableLocales())
                .filter(lcl ->
                {
                    try {
                        return ResourceBundle.getBundle("Bundle", lcl).getLocale() == lcl;
                    } catch (Exception e) {
                        return false;
                    }
                })
                .collect(Collectors.toSet());
    }
}
