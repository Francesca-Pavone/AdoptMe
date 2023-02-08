package com.ispwproject.adoptme.engineering.exception.Fra;

public class ImageNotFoundException extends Exception{
    public ImageNotFoundException() {
        super("Image not loaded, the default image will be set");
    }
}
