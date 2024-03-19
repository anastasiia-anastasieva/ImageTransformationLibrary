package image;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/** основний клас для обробки зображень **/

public class ImageProcessor {

    /**
     * Масштабує зображення до заданих розмірів.
     * @param original Зображення для масштабування.
     * @param width Ширина нового зображення.
     * @param height Висота нового зображення.
     * @return Масштабоване зображення.
     */
    public static BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.drawImage(original, 0, 0, width, height, null);
        g2d.dispose();
        return scaledImage;
    }
}