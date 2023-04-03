import com.mab8192.rt.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        double aspectRatio = 16.0/9.0;
        int width = 400;
        int height = (int) (width / aspectRatio);

        // Camera
        double viewportHeight = 2.0;
        double viewportWidth = aspectRatio * viewportHeight;
        double focal_length = 1.0;

        Vec3 origin = new Vec3();
        Vec3 horizontal = new Vec3(viewportWidth, 0, 0);
        Vec3 vertical = new Vec3(0, viewportHeight, 0);

        Vec3 lowerLeftCorner = origin.sub(horizontal.div(2)).sub(vertical.div(2)).sub(new Vec3(0, 0, focal_length));

        System.out.println(horizontal);
        System.out.println(vertical);
        System.out.println(lowerLeftCorner);

        PPMImage img = new PPMImage(width, height);

        for (int j = height-1; j >= 0; j--) {
            System.out.println("Scanlines remaining: " + j);
            for (int i = 0; i < width; i++) {
                double u = (double)i/width;
                double v = (double)j/height;
                Ray r = new Ray(origin, lowerLeftCorner.add(horizontal.mul(u)).add(vertical.mul(v)).sub(origin));
                img.writeColor(r.getColor());
            }
        }

        try {
            img.save("image.ppm");
        } catch (IOException e) {
            System.err.println("Failed to save image!");
        }
    }
}