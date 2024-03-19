
import image.ImageProcessor;
import io.ImageUtils;
import io.ImageLoader;
import image.ImageSaver;

import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {
        try {
            String outputImagePath;
            // Завантаження зображення
            BufferedImage image = ImageLoader.loadImage("D:/image-7.jpg");
            if (image == null) {
                System.err.println("Не вдалося завантажити зображення.");
                return;
            }

            // Зміна яскравості зображення
            BufferedImage brighterImage = ImageUtils.changeBrightness(image, 80);

            // Масштабування зображення
            BufferedImage scaledImage = ImageProcessor.scaleImage(brighterImage, 700, 280);

            // Збереження зображення у форматі PNG
            ImageSaver.saveImage(scaledImage, "PNG", outputImagePath = "D:/image_scaled-1.png");

            // Конвертація оригінального зображення в JPG
            //ImageUtils.convertToJpg(image, outputImagePath = "path/to/save/image_converted.jpg");

            // Відображення збереженого зображення
            BufferedImage outputImage = ImageLoader.loadImage(outputImagePath);
            if (outputImage != null) {
                ImageUtils.displayImage(outputImage, "Результат");
            } else {
                System.out.println("Не вдалося завантажити збережене зображення.");
            }

            System.out.println("Демонстрація завершена успішно.");
        } catch (Exception e) {
            System.err.println("Помилка під час виконання демонстрації: " + e.getMessage());
        }
    }
}