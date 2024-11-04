package com.example.projectdemo.model;

import javafx.scene.image.ImageView;

public class LanguageOption {
    private final String language;
    private final ImageView imageView;

    public LanguageOption(String language, ImageView imageView) {
        this.language = language;
        this.imageView = imageView;
    }

    public String getLanguage() {
        return language;
    }

    public ImageView getImageView() {
        return imageView;
    }

    @Override
    public String toString() {
        return language; // This is optional, used for debugging purposes
    }
}