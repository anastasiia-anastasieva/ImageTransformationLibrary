package transformation; // Директива в якій знаходиться клас

import java.awt.Graphics2D; // Імпортує клас Graphics2D для розширених можливостей малювання (наприклад, масштабування).
import java.awt.image.BufferedImage; // Імпортує клас для роботи з зображеннями, які зберігаються у пам'яті

/** Клас для реалізації операції зміни розміру, що наслідується від AbstractImageOperation */
public class ScaleOperation extends AbstractImageOperation {

    private int width, height; // Зберігає цільові розміри зображення після масштабування.

    // Конструктор, що приймає ширину і висоту як параметри для нового розміру зображення.
    public ScaleOperation(int width, int height) {
        super("Scale Operation"); // Викликає конструктор базового класу з назвою операції.
        this.width = width; // Ініціалізація ширини для масштабованого зображення.
        this.height = height; // Ініціалізація висоти для масштабованого зображення.
    }

    // Перевизначений метод для виконання операції масштабування зображення.
    @Override
    protected BufferedImage doOperation(BufferedImage original) {
        // Створення зображення нового розміру
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2d = scaledImage.createGraphics(); // Створення графічного контексту для малювання на зображенні.
        // Масштабування оригінального зображення до нових розмірів
        g2d.drawImage(original, 0, 0, width, height, null);
        // Звільнення ресурсів графічного контексту.
        g2d.dispose();
        // Повернення масштабованого зображення.
        return scaledImage;
    }
}
