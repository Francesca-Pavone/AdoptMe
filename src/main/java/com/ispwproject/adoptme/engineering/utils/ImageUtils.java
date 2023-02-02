package com.ispwproject.adoptme.engineering.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;

public class ImageUtils {
    //Costruttore privato
    private ImageUtils() {
        //Costruttore privato perché ho tutti i metodi statici
    }

    public static Image fromFileToImage(File file) throws IOException {
        BufferedImage bfImage = null;
        bfImage = ImageIO.read(file);

        WritableImage writableImage = null;
        if (bfImage != null) {
            writableImage = new WritableImage(bfImage.getWidth(), bfImage.getHeight());
            PixelWriter pw = writableImage.getPixelWriter();
            for (int x = 0; x < bfImage.getWidth(); x++) {
                for (int y = 0; y < bfImage.getHeight(); y++) {
                    pw.setArgb(x, y, bfImage.getRGB(x, y));
                }
            }
        }
        return new ImageView(writableImage).getImage();
    }

    public static File fromBlobToFile(Blob blob, String filePath) throws IOException, SQLException {
        //TODO: vedere se trovo un altro modo invece di mantenere un nuovo file per ogni immagine
        InputStream inputStream = blob.getBinaryStream();
        File file = new File(filePath);
        FileOutputStream outputStream = new FileOutputStream(file);
        int read;
        byte[] bytes = new byte[4096];
        while ((read = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }
        return file;
    }

}
