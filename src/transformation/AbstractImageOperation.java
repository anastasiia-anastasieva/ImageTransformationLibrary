package transformation; // Директива в якій знаходиться клас

import java.awt.image.BufferedImage; // Імпортує клас BufferedImage для роботи з зображеннями

// Оголошує абстрактний клас, який слугує базовим для операцій зі зміною зображень
public abstract class AbstractImageOperation implements ImageOperation {
    // Захищене поле для зберігання назви операції
    protected String operationName;

    // Конструктор, що дозволяє задати назву операції при створенні об'єкту
    protected AbstractImageOperation(String operationName) {
        this.operationName = operationName;
    }

    // Реалізує метод інтерфейсу ImageOperation
    @Override
    public BufferedImage apply(BufferedImage original) {
        // Виводить інформацію про застосування операції
        System.out.println("Applying " + operationName);
        // Викликає абстрактний метод doOperation, який має бути реалізований у підкласах
        return doOperation(original);
    }

    // Абстрактний метод для реалізації специфічної логіки операції в підкласах
    protected abstract BufferedImage doOperation(BufferedImage original);
}