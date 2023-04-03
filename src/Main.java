import com.mab8192.rt.*;

import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        double aspectRatio = 16.0/9.0;
        int width = 400;
        int height = (int) (width / aspectRatio);
        int samplesPerPixel = 100;

        // Camera
        Camera camera = new Camera(aspectRatio);

        // World
        Scene world = new Scene();
        world.add(new Sphere(new Vec3(0, 0, -1), 0.5));
        world.add(new Sphere(new Vec3(0, -100.5, -1), 100));

        // Image
        PPMImage img = new PPMImage(width, height);

        // Util
        Random rand = new Random();

        for (int j = height-1; j >= 0; j--) {
            System.out.println("Scanlines remaining: " + j);
            for (int i = 0; i < width; i++) {
                Vec3 color = new Vec3();
                for (int s = 0; s < samplesPerPixel; s++) {
                    double u = (i + rand.nextDouble())/width;
                    double v = (j + rand.nextDouble())/height;

                    Ray r = camera.getRay(u, v);
                    color = color.add(r.getColor(world));
                }

                img.writeColor(color, samplesPerPixel);
            }
        }

        try {
            img.save("image.ppm");
        } catch (IOException e) {
            System.err.println("Failed to save image!");
        }
    }
}