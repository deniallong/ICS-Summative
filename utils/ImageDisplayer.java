package utils;

import java.awt.image.ImageObserver;
import java.awt.Image;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import hsa.Console;

public class ImageDisplayer implements ImageObserver {

    static Console c;

    static 
    {
        c = new Console(30, 75);
    }
    
    public void display(File file, int x, int y) throws IOException 
    {
        // Creating the image object
        Image image = ImageIO.read(file);

        // Drawing the image
        c.drawImage(image, x, y, this);
    }

    // (Method that must be included because of the ImageObserver interface, ignore this)
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) 
    {
        return false;
    }
}
