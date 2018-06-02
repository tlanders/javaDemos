package jse8_impatient.chapter3;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

/**
 * Created by tlanders.
 */
public class Exercise5 {
    private static Logger logger = Logger.getLogger(Exercise5.class.getName());

    public static void main(String [] args) throws Exception {
        System.out.println("Exercise5");

        if (args.length < 1) {
            System.out.println("USAGE: java Exercise5 INPUT_IMAGE");
            System.exit(1);
        }

        Image image = new Image(args[0]);

//        Image transformedImage = transform(image, transformer);
        System.out.println("Exercise5, done");
    }

    public static Image transform(Image image, ColorTransformer transfomer) {
        return image;
    }
}

@FunctionalInterface
interface ColorTransformer {
    Color apply(int x, int y, Color colorAtXY);
}
