package transformation; // Директива в якій знаходиться клас
import java.awt.image.BufferedImage; // Імпортує клас для роботи з зображеннями, які зберігаються у пам'яті

public interface ImageOperation {
    // Метод для застосування операції до зображення
    BufferedImage apply(BufferedImage original);
}