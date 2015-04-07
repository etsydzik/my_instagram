package by.tsydzik.eugene.service;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by tsyd on 18.03.2015.
 */
@Component

public class ImageService {

    public byte[] resize(BufferedImage bufferedImage, int maxWidth, int maxHeight) throws IOException {
        int height = bufferedImage.getHeight();
        int width = bufferedImage.getWidth();
        double v = calculateScale(width, height, maxWidth, maxHeight);
        BufferedImage resizedCopy = createResizedCopy(bufferedImage, (int) (v * width), (int) (v * height));
        return toByteArray(resizedCopy);
    }

    private byte[] toByteArray(BufferedImage image) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", bos);
        return bos.toByteArray();
    }

    private double calculateScale(int width, int height, int maxWidth, int maxHeight) {
        double k1 = (double)maxHeight / height;
        double k2 = (double)maxWidth / width;
        return Math.min(k1, k2);
    }

    static BufferedImage createResizedCopy(Image originalImage,
                                           int scaledWidth, int scaledHeight) {
        int imageType = BufferedImage.TYPE_INT_RGB;
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight,
                imageType);
        Graphics2D g = scaledBI.createGraphics();
        g.setComposite(AlphaComposite.Src);
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }
}
