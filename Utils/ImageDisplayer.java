package utils;

import java.awt.image.ImageObserver;
import java.awt.Image;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import hsa.Console;

public class ImageDisplayer implements ImageObserver {
    
    public void display(File file, int x, int y, Console c) throws IOException {

        Image image = ImageIO.read(file);

        c.drawImage(image, x, y, this);
    }

    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }
}
