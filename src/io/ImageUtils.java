package io;

//бібліотеки для роботи з насиченістю кольорів
import java.awt.Color;
import java.awt.image.BufferedImage;

//бібліотеки для конвертації зображення в формати: .png & .jpg
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

//бібліотеки для автоматичного відкриття зображення після виконання
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/** клас для масштабування зображень **/

public class ImageUtils {
    /**
     * Змінює яскравість зображення.
     * @param original Зображення, для якого потрібно змінити яскравість.
     * @param brightnessChange Зміна яскравості (може бути позитивною або негативною).
     * @return Зображення з зміненою яскравістю.
     */
    public static BufferedImage changeBrightness(BufferedImage original, int brightnessChange) {
        BufferedImage result = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());

        for (int x = 0; x < original.getWidth(); x++) {
            for (int y = 0; y < original.getHeight(); y++) {
                Color originalColor = new Color(original.getRGB(x, y));

                int red = originalColor.getRed() + brightnessChange;
                int green = originalColor.getGreen() + brightnessChange;
                int blue = originalColor.getBlue() + brightnessChange;

                // Виправлення значень кольору, щоб вони залишалися в допустимому діапазоні 0-255
                red = Math.min(Math.max(red, 0), 255);
                green = Math.min(Math.max(green, 0), 255);
                blue = Math.min(Math.max(blue, 0), 255);

                Color newColor = new Color(red, green, blue);
                result.setRGB(x, y, newColor.getRGB());
            }
        }

        return result;
    }

    /**
     * Конвертує зображення в JPG і зберігає за вказаним шляхом.
     * @param image Зображення для конвертації.
     * @param path Шлях, де буде збережено зображення.
     */
    public static void convertToJpg(BufferedImage image, String path) throws IOException {
        // Видалення альфа-каналу для JPG
        BufferedImage newBufferedImage = new BufferedImage(image.getWidth(),
                image.getHeight(), BufferedImage.TYPE_INT_RGB);
        newBufferedImage.createGraphics().drawImage(image, 0, 0, null);
        ImageIO.write(newBufferedImage, "JPG", new File(path));
    }

    /**
     * Конвертує зображення в PNG і зберігає за вказаним шляхом.
     * @param image Зображення для конвертації.
     * @param path Шлях, де буде збережено зображення.
     */
    public static void convertToPng(BufferedImage image, String path) throws IOException {
        ImageIO.write(image, "PNG", new File(path));
    }

    /**
     * Відображає зображення у новому вікні.
     * @param image Зображення для відображення.
     * @param title Назва вікна.
     */
    public static void displayImage(BufferedImage image, String title) {
        // Перевіряємо, чи зображення не є null
        if (image != null) {
            // Створюємо вікно для відображення зображення
            JFrame frame = new JFrame(title);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(image.getWidth(), image.getHeight());

            // Панель для відображення зображення
            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    // Малюємо зображення у вікні
                    g.drawImage(image, 0, 0, this);
                }
            };
            frame.add(panel);
            frame.setVisible(true);
        }
    }
}