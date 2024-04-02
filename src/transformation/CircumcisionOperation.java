package transformation; // Директива в якій знаходиться клас

import java.awt.image.BufferedImage; // Імпортує клас для роботи з зображеннями, які зберігаються у пам'яті

/** Клас для реалізації операції обрізання зображення, що наслідується від AbstractImageOperation */
public class CircumcisionOperation extends AbstractImageOperation {

    private final double topPercentage;
    private final double bottomPercentage;
    private final double leftPercentage;
    private final double rightPercentage;

    /**
     * Конструктор для ініціалізації відсотків обрізки.
     * @param topPercentage Відсоток обрізки зверху.
     * @param bottomPercentage Відсоток обрізки знизу.
     * @param leftPercentage Відсоток обрізки зліва.
     * @param rightPercentage Відсоток обрізки справа.
     */
    public CircumcisionOperation(double topPercentage, double bottomPercentage, double leftPercentage, double rightPercentage) {
        // Викликає конструктор базового класу з назвою операції
        super("Circumcision Operation");
        // Ініціалізує поля з заданими відсотками обрізки
        this.topPercentage = topPercentage;
        this.bottomPercentage = bottomPercentage;
        this.leftPercentage = leftPercentage;
        this.rightPercentage = rightPercentage;
    }

    // Реалізує метод обрізки зображення
    @Override
    protected BufferedImage doOperation(BufferedImage original) {
        int height = original.getHeight(); // Отримує висоту оригінального зображення
        int width = original.getWidth(); // Отримує ширину оригінального зображення

        // Розрахунок координат для обрізки на основі відсотків
        int topY = (int) (height * topPercentage);
        int bottomY = height - (int) (height * bottomPercentage);
        int leftX = (int) (width * leftPercentage);
        int rightX = width - (int) (width * rightPercentage);

        // Визначення нових розмірів обрізаного зображення
        int newWidth = rightX - leftX;
        int newHeight = bottomY - topY;

        // Створення обрізаного зображення з використанням розрахованих координат і розмірів
        BufferedImage croppedImage = original.getSubimage(leftX, topY, newWidth, newHeight);
        // Повертає обрізане зображення
        return croppedImage;
    }
}
