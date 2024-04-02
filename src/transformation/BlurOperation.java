package transformation; // Директива в якій знаходиться клас

import java.awt.image.BufferedImage; // Імпортує клас для роботи з зображеннями, які зберігаються у пам'яті

/** Клас для реалізації операції розмиття зображення, що наслідується від AbstractImageOperation */
public class BlurOperation extends AbstractImageOperation {

    // Задає радіус розмиття
    private int radius;

    // Конструктор, що ініціалізує операцію розмиття з заданим радіусом
    public BlurOperation(int radius) {
        super("Blur Operation"); // Викликає конструктор базового класу з назвою операції
        this.radius = radius; // Ініціалізує радіус розмиття
    }

    // Реалізує метод doOperation базового класу для виконання розмиття
    @Override
    protected BufferedImage doOperation(BufferedImage original) {
        int width = original.getWidth(); // Отримує ширину оригінального зображення
        int height = original.getHeight(); // Отримує висоту оригінального зображення

        // Створює тимчасове зображення для виконання розмиття
        BufferedImage tempImage = new BufferedImage(width, height, original.getType());
        // Створює кінцеве зображення для збереження результату розмиття
        BufferedImage blurredImage = new BufferedImage(width, height, original.getType());

        // Горизонтальний прохід розмиття
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixelCount = 0;
                int red = 0;
                int green = 0;
                int blue = 0;

                // Перебір пікселів навколо поточного для вирішення середнього кольору
                for (int dx = -radius; dx <= radius; dx++) {
                    int nx = x + dx;
                    // Перевірка меж зображення
                    if (nx >= 0 && nx < width) {
                        int color = original.getRGB(nx, y);
                        // Розбивання кольору на складові і додавання до загальної суми
                        red += (color >> 16) & 0xFF;
                        green += (color >> 8) & 0xFF;
                        blue += color & 0xFF;
                        pixelCount++;
                    }
                }

                // Розрахунок середнього кольору
                int avgRed = red / pixelCount;
                int avgGreen = green / pixelCount;
                int avgBlue = blue / pixelCount;
                // Формування кольору пікселя із середніх значень
                int avgColor = (avgRed << 16) | (avgGreen << 8) | avgBlue;

                // Встановлення пікселя у тимчасове зображення
                tempImage.setRGB(x, y, avgColor);
            }
        }

        // Вертикальний прохід розмиття
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixelCount = 0;
                int red = 0;
                int green = 0;
                int blue = 0;

                // Аналогічно горизонтальному проходу, але у вертикальному напрямку
                for (int dy = -radius; dy <= radius; dy++) {
                    int ny = y + dy;
                    // Перевірка меж зображення
                    if (ny >= 0 && ny < height) {
                        int color = tempImage.getRGB(x, ny);
                        // Розбивання кольору на складові і додавання до загальної суми
                        red += (color >> 16) & 0xFF;
                        green += (color >> 8) & 0xFF;
                        blue += color & 0xFF;
                        pixelCount++;
                    }
                }

                // Розрахунок середнього кольору для вертикального проходу
                int avgRed = red / pixelCount;
                int avgGreen = green / pixelCount;
                int avgBlue = blue / pixelCount;
                // Формування кольору пікселя із середніх значень для вертикального проходу
                int avgColor = (avgRed << 16) | (avgGreen << 8) | avgBlue;

                // Встановлення пікселя у кінцеве зображення
                blurredImage.setRGB(x, y, avgColor);
            }
        }

        // Повернення зображення після розмиття
        return blurredImage;
    }
}
