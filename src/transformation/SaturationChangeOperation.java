package transformation; // Директива в якій знаходиться клас

import java.awt.Color; // Імпортує клас Color для роботи з кольорами.
import java.awt.image.BufferedImage; // Імпортує клас для роботи з зображеннями, які зберігаються у пам'яті

/** Клас для реалізації операції зміни насиченості, що наслідується від AbstractImageOperation */
public class SaturationChangeOperation extends AbstractImageOperation {
    private float saturationPercentage; // Насиченість задана в процентах

    // Конструктор класу, який ініціалізує об'єкт з заданим значенням насиченості.
    public SaturationChangeOperation(float saturationPercentage) {
        super("Saturation Change Operation"); // Зберігає насиченість у відсотках для майбутнього використання у операції.
        this.saturationPercentage = saturationPercentage; // Ініціалізує поле насиченості заданим значенням.
    }

    // Перевизначений метод для виконання операції зміни насиченості зображення.
    @Override
    protected BufferedImage doOperation(BufferedImage original) {
        int width = original.getWidth(); // Отримання ширини оригінального зображення.
        int height = original.getHeight(); // Отримання висоти оригінального зображення.
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Конвертуємо відсоток насиченості в множник (наприклад, 50% стає 0.5, 100% залишається 1)
        float saturationFactor = saturationPercentage / 100.0f;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = original.getRGB(x, y); // Отримання кольору пікселя.
                int alpha = (rgb >> 24) & 0xff; // Виділення альфа-компонента (прозорість).
                // Перетворення RGB значення кольору в HSB для маніпуляції насиченістю.
                float[] hsb = Color.RGBtoHSB((rgb >> 16) & 0xff, (rgb >> 8) & 0xff, rgb & 0xff, null);

                // Зміна насиченості з урахуванням нового множника
                hsb[1] = clamp(hsb[1] * saturationFactor, 0f, 1f);

                // Повернення до RGB формату з урахуванням нової насиченості та збереження альфа-компоненти.
                int newRgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]) | (alpha << 24);
                result.setRGB(x, y, newRgb); // Запис зміненого кольору назад у зображення
            }
        }
        return result; // Повернення результуючого зображення зі зміненою насиченістю.
    }

    // Допоміжний метод для обмеження значення вказаного діапазоном.
    private float clamp(float val, float min, float max) {

        return Math.max(min, Math.min(max, val)); // Повертає значення, обмежене мінімумом та максимумом.
    }
}
