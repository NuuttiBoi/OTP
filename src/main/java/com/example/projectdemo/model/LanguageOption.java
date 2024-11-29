package com.example.projectdemo.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Class for different language options.
 */
public class LanguageOption {
    private final String language;
    private final ImageView imageView;
    private final Image image;

    public LanguageOption(String language, ImageView imageView, Image image) throws IOException {
        this.language = language;
        this.imageView = imageView;
        this.image = image;
    }

    public String getLanguage() {
        return language;
    }

    public ImageView getImageView() {
        return imageView;
    }
    public Image getImage(){return image;}

    @Override
    public String toString() {
        return language;
    }
}