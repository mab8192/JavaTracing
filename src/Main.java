import com.mab8192.rt.*;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int width = 256, height = 256;

        PPMImage img = new PPMImage(width, height);

        for (int j = height-1; j >= 0; j--) {
            System.err.println("Scanlines remaining: " + j);
            for (int i = 0; i < width; i++) {
                img.writeColor(new Vec3((double)i/width, (double)j/height, 0.25));
            }
        }

        try {
            img.save("image.ppm");
        } catch (IOException e) {
            System.err.println("Failed to save image!");
        }
    }
}