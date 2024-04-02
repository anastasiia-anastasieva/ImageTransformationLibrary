package image; // Директива в якій знаходиться клас

import javax.imageio.ImageIO; // Імпортує клас ImageIO для роботи з читанням/записом зображень
import java.awt.image.BufferedImage; // Імпортує клас BufferedImage для представлення зображень, які можуть бути збережені
import java.io.File; // Імпортує клас File для роботи з файловою системою
import java.io.IOException; // Імпортує клас IOException для обробки помилок вводу/виводу

/** Клас для збереження зображення за вказаним шляхом **/

public class ImageSaver {

    /**
     * Зберігає зображення у файл.
     * @param image Зображення для збереження.
     * @param format Формат файлу (наприклад, "PNG" або "JPG").
     * @param path Шлях до файлу, де буде збережено зображення.
     */

    // Статичний метод для збереження зображення
    public static void saveImage(BufferedImage image, String format, String path) {
        try {
            // Створює об'єкт файлу за заданим шляхом
            File file = new File(path);
            // Спроба записати зображення в файл у заданому форматі
            ImageIO.write(image, format, file);
            // Виводить повідомлення про успішне збереження зображення
            System.out.println("Image saved to " + path);
        } catch (IOException e) { // Якщо виникає помилка вводу/виводу під час збереження файла
            System.err.println("Error saving image: " + e.getMessage());
        }
    }
}