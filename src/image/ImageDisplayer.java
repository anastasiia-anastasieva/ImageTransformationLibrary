package image; // Директива в якій знаходиться клас

import javax.swing.*; // Імпортує класи для створення графічного інтерфейсу користувача
import java.awt.*; // Імпортує класи для роботи з базовими компонентами та графікою
import java.awt.image.BufferedImage; // Імпортує клас для роботи з зображеннями, які зберігаються у пам'яті

/** Клас для відображення зображення */
public class ImageDisplayer {
    /**
     * Відображає зображення у новому вікні, зберігаючи пропорції зображення.
     * @param image Зображення для відображення.
     * @param title Назва вікна.
     */

    // Статичний метод для відображення зображення у новому вікні
    public static void displayImage(BufferedImage image, String title) {
        // Створює нове вікно з заданою назвою
        JFrame frame = new JFrame(title);
        // Встановлює операцію, яка виконається при закритті вікна (вийти з програми)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Встановлює розміри вікна
        frame.setSize(1200, 700);
        // Розміщує вікно по центру екрана
        frame.setLocationRelativeTo(null);

        // Створює анонімний клас JPanel для малювання зображення
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                // Викликає реалізацію методу з суперкласу для коректного відображення компонентів
                super.paintComponent(g);
                // Визначення розміру панелі
                Dimension size = this.getSize();
                // Визначення пропорцій зображення та панелі
                double imgWidth = image.getWidth(); // Отримує ширину зображення
                double imgHeight = image.getHeight(); // Отримує ширину зображення
                double containerWidth = size.getWidth(); // Отримує ширину контейнера (панелі)
                double containerHeight = size.getHeight(); // Отримує висоту контейнера (панелі)

                // Розрахунок нових розмірів зображення для збереження пропорцій
                double scale = Math.min(containerWidth / imgWidth, containerHeight / imgHeight);

                int newImgWidth = (int) (imgWidth * scale); // Розраховує нову ширину зображення з урахуванням масштабу
                int newImgHeight = (int) (imgHeight * scale); // Розраховує нову висоту зображення з урахуванням масштабу

                // Вирівнювання зображення по центру
                int x = (int) (containerWidth - newImgWidth) / 2; // Визначає позицію по горизонталі для центрування зображення
                int y = (int) (containerHeight - newImgHeight) / 2; // Визначає позицію по вертикалі для центрування зображення

                // Малює зображення на панелі з урахуванням розрахованих розмірів і позиції
                g.drawImage(image, x, y, newImgWidth, newImgHeight, this);
            }
        };

        frame.add(panel); // Додає панель з малюванням до вікна
        frame.setVisible(true); // Робить вікно видимим, що дозволяє користувачу бачити зображення
    }
}