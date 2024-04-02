package image; // Директива в якій знаходиться клас

import javax.imageio.ImageIO; // Імпортує клас ImageIO для роботи з читанням/записом зображень
import java.awt.image.BufferedImage; // Імпортує клас BufferedImage для представлення зображень, які завантажуються
import java.io.File; // Імпортує клас File для роботи з файловою системою
import java.io.IOException; // Імпортує клас IOException для обробки помилок вводу/виводу

/** Клас для завантаження зображення */

public class ImageLoader {

    // Статичний метод для завантаження зображення з файлу за заданим шляхом
    public static BufferedImage loadImage(String path) {
        try {
            // Спроба прочитати зображення з файлу і повернути його як BufferedImage
            return ImageIO.read(new File(path));
        } catch (IOException e) { // Якщо виникає помилка вводу/виводу під час читання файла
            System.err.println("Error loading image: " + e.getMessage());
            return null; // Повертає null, якщо зображення не може бути завантажене
        }
    }
}
