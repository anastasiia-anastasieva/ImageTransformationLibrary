package transformation; // Директива в якій знаходиться клас

import java.awt.Color; // Імпортує клас Color для роботи з кольорами
import java.awt.image.BufferedImage; // Імпортує клас для роботи з зображеннями, які зберігаються у пам'яті

/** Клас для зміни яскравості, що наслідується від AbstractImageOperation */
public class BrightnessChangeOperation extends AbstractImageOperation {

    // Змінна для зберігання величини зміни яскравості
    private int brightnessChange;

    // Конструктор, що приймає величину зміни яскравості як параметр
    public BrightnessChangeOperation(int brightnessChange) {
        // Викликає конструктор батьківського класу з назвою операції
        super("Brightness Change Operation");
        // Ініціалізує змінну зміни яскравості
        this.brightnessChange = brightnessChange;
    }

    // Реалізація методу зміни яскравості
    @Override
    protected BufferedImage doOperation(BufferedImage original) {
        // Створює нове зображення з такими ж розмірами та типом, як у оригінального
        BufferedImage result = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());

        // Проходить через кожен піксель оригінального зображення
        for (int x = 0; x < original.getWidth(); x++) {
            for (int y = 0; y < original.getHeight(); y++) {
                // Отримує колір поточного пікселя
                Color originalColor = new Color(original.getRGB(x, y), true);

                // Змінює яскравість кольорів пікселя
                int red = originalColor.getRed() + brightnessChange;
                int green = originalColor.getGreen() + brightnessChange;
                int blue = originalColor.getBlue() + brightnessChange;

                // Коригує значення кольорів, щоб вони не виходили за межі можливих значень
                red = Math.min(Math.max(red, 0), 255);
                green = Math.min(Math.max(green, 0), 255);
                blue = Math.min(Math.max(blue, 0), 255);

                // Створює новий колір зі зміненими значеннями
                Color newColor = new Color(red, green, blue, originalColor.getAlpha());
                // Застосовує новий колір до пікселя в результатному зображенні
                result.setRGB(x, y, newColor.getRGB());
            }
        }
        // Повертає зображення після застосування зміни яскравості
        return result;
    }
}
