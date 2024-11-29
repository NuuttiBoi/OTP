package com.example.projectdemo.model;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class for setting a language.
 */
public class LanguageManager {
    private static ResourceBundle resourceBundle;

    public static void setLocale(Locale locale) {
        resourceBundle = ResourceBundle.getBundle("messages", locale);
    }

    public static ResourceBundle getResourceBundle() {
        return resourceBundle;
    }
}