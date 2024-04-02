import image.ImageLoader;
import image.ImageSaver;
import image.ImageDisplayer;
import transformation.ImageOperation;
import transformation.BlurOperation;
import transformation.RotateOperation;
import transformation.CircumcisionOperation;
import transformation.BrightnessChangeOperation;
import transformation.SaturationChangeOperation;
import transformation.CropOperation;
import transformation.ScaleOperation;

import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {
        try {
            // Шлях до оригінального зображення
            String imagePath = "C:/img-for-kr/art.jpg";
            // Шлях, де буде збережено змінене зображення
            String outputImagePath = "C:/img-for-kr/result/art0.png";

            // Завантаження зображення
            BufferedImage image = ImageLoader.loadImage(imagePath);
            if (image == null) {
                System.err.println("Не вдалося завантажити зображення.");
                return;
            }


            // Створення і застосування операцій
            BufferedImage processedImage;

            // Створення операції повороту і застосування її до зображення
            ImageOperation rotateOp = new RotateOperation(180); // Поворот на N градусів
            processedImage = rotateOp.apply(image);

            // Створення фільтру розмиття
            //ImageOperation blurOp = new BlurOperation(10);
            //processedImage = blurOp.apply(processedImage); // Застосування фільтру розмиття

            // Створення об'єкта для обрізки зображення i обрізати 20% з кожної сторони
            ImageOperation circumcisionOp = new CircumcisionOperation(0.2, 0.2, 0.2, 0.2);
            processedImage = circumcisionOp.apply(processedImage);

            // Зміна якравості
            ImageOperation brightnessOp = new BrightnessChangeOperation(80);
            processedImage = applyOperation(processedImage, brightnessOp);

            // Зміна розміру
            ImageOperation scaleOp = new ScaleOperation(700, 500);
            processedImage = applyOperation(processedImage, scaleOp);

            // Зміна насиченості (наприклад, збільшення на 50%)
            ImageOperation saturationOp = new SaturationChangeOperation(150); // 150% насиченості
            processedImage = saturationOp.apply(processedImage);

            // Вирізати частину зображення, починаючи з координат (x, y) розміром 1000x800 пікселів
            ImageOperation cropOp = new CropOperation(320, 90, 340, 220);
            processedImage = cropOp.apply(processedImage);


            // Збереження зображення у файл
            ImageSaver.saveImage(processedImage, "PNG", outputImagePath);

            // Відображення зміненого зображення
            ImageDisplayer.displayImage(processedImage, "Результат операцій");

            System.out.println("Трансформація зображення завершена успішно.");
        } catch (Exception e) {
            System.err.println("Помилка під час виконання демонстрації: " + e.getMessage());
        }
    }

    /**
     * Застосовує задану операцію до зображення.
     * @param original Оригінальне зображення.
     * @param operation Операція, яку потрібно застосувати.
     * @return Зображення після застосування операції.
     */
    public static BufferedImage applyOperation(BufferedImage original, ImageOperation operation) {
        return operation.apply(original);
    }
}