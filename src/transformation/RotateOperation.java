package transformation; // Директива в якій знаходиться клас

import java.awt.Graphics2D; // Імпортує клас Graphics2D для роботи з графікою на високому рівні.
import java.awt.geom.AffineTransform; // Імпортує клас AffineTransform для роботи з трансформацією координат.
import java.awt.image.BufferedImage; // Імпортує клас BufferedImage для роботи з зображеннями, які можна модифікувати.

/** Клас для реалізації операції повороту на N градусів, що наслідується від AbstractImageOperation */
public class RotateOperation extends AbstractImageOperation {

    private double angle; // Приватне поле для зберігання кута повороту в градусах.

    // Конструктор, який приймає кут повороту як параметр і викликає конструктор батьківського класу з назвою операції.
    public RotateOperation(double angle) {
        super("Rotate Operation"); // Виклик конструктора базового класу з назвою операції.
        this.angle = angle; // Ініціалізація кута повороту.
    }

    // Перевизначення методу для виконання операції повороту зображення.
    @Override
    protected BufferedImage doOperation(BufferedImage original) {
        int w = original.getWidth(); // Отримання ширини оригінального зображення.
        int h = original.getHeight(); // Отримання висоти оригінального зображення.
        double radians = Math.toRadians(angle); // Переведення кута повороту з градусів у радіани.

        // Визначаємо розміри нового зображення
        double sin = Math.abs(Math.sin(radians)), cos = Math.abs(Math.cos(radians));
        int newWidth = (int) Math.floor(w * cos + h * sin); // Ширина нового зображення.
        int newHeight = (int) Math.floor(h * cos + w * sin); // Висота нового зображення.

        // Створюємо нове зображення з підтримкою прозорості
        BufferedImage rotatedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotatedImage.createGraphics(); // Створення графічного контексту для нового зображення.

        // Встановлюємо початкове положення та обертаємо зображення
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2); // Вирівнювання зображення по центру.
        int x = w / 2;
        int y = h / 2;
        at.rotate(radians, x, y); // Встановлення кута повороту зображення відносно центру.
        g2d.setTransform(at); // Застосування трансформації до контексту.

        // Виконуємо малювання з прозорістю
        g2d.drawImage(original, 0, 0, null);
        g2d.dispose(); // Звільнення ресурсів графічного контексту.

        return rotatedImage; // Повернення результату – повернутого зображення.
    }
}
