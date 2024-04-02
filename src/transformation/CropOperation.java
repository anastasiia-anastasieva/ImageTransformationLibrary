package transformation; // Директива в якій знаходиться клас

import java.awt.image.BufferedImage; // Імпортує клас для роботи з зображеннями, які зберігаються у пам'яті

/** Клас для реалізації операції вирізання частини зображення, що наслідується від AbstractImageOperation */
public class CropOperation extends AbstractImageOperation {

    private int x, y, width, height; // Поля для збереження координат та розмірів вирізаної частини

    // Конструктор для ініціалізації координат та розмірів вирізаної частини
    public CropOperation(int x, int y, int width, int height) {
        // Викликає конструктор базового класу з назвою операції
        super("Crop Operation");
        this.x = x; // Ініціалізація початкової x координати
        this.y = y; // Ініціалізація початкової y координати
        this.width = width; // Ініціалізація ширини вирізаної частини
        this.height = height; // Ініціалізація висоти вирізаної частини
    }

    @Override
    protected BufferedImage doOperation(BufferedImage original) {
        // Перевіряємо, що координати та розміри не виходять за межі оригінального зображення
        int newX = Math.max(x, 0); // Коригування x координати, якщо вона виходить за межі зображення
        int newY = Math.max(y, 0); // Коригування y координати, якщо вона виходить за межі зображення
        int newWidth = Math.min(width, original.getWidth() - newX); // Коригування ширини, якщо вона виходить за межі зображення
        int newHeight = Math.min(height, original.getHeight() - newY); // Коригування висоти, якщо вона виходить за межі зображення

        // Використовуємо метод getSubimage для вирізання частини зображення
        BufferedImage croppedImage = original.getSubimage(newX, newY, newWidth, newHeight);
        // Повертаємо вирізане зображення
        return croppedImage;
    }
}
