package com.ispwproject.adoptme.engineering.exception;

public class ImageNotFoundException extends Exception{
    public ImageNotFoundException() {
        super("Image not loaded, the default image will be set");
    }
}
