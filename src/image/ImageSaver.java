package image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/** клас для збереження зображень у файл **/

public class ImageSaver {

    /**
     * Зберігає зображення у файл.
     * @param image Зображення для збереження.
     * @param format Формат файлу (наприклад, "PNG" або "JPG").
     * @param path Шлях до файлу, де буде збережено зображення.
     */
    public static void saveImage(BufferedImage image, String format, String path) {
        try {
            File file = new File(path);
            ImageIO.write(image, format, file);
            System.out.println("Image saved to " + path);
        } catch (IOException e) {
            System.err.println("Error saving image: " + e.getMessage());
        }
    }
}