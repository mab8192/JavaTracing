import com.mab8192.rt.*;

import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        double aspectRatio = 16.0/9.0;
        int width = 400;
        int height = (int) (width / aspectRatio);
        int samplesPerPixel = 100;
        int numBounces = 100;

        // Camera
        Camera camera = new Camera(aspectRatio);

        // World
        Scene world = new Scene();

        Material groundMaterial = new Lambertian(new Vec3(0.4, 0.8, 0.0));
        Material centerMaterial = new Lambertian(new Vec3(0.9, 0.3, 0.3));
        Material leftMaterial = new Metal(new Vec3(0.8, 0.8, 0.8), 0.3);
        Material rightMaterial = new Metal(new Vec3(0.8, 0.6, 0.2), 1.0);

        world.add(new Sphere(new Vec3(0, -100.5, -1), 100, groundMaterial));
        world.add(new Sphere(new Vec3(0, 0, -1), 0.25, centerMaterial));
        world.add(new Sphere(new Vec3(-1, 0, -1), 0.5, leftMaterial));
        world.add(new Sphere(new Vec3(1, 0, -1), 0.5, rightMaterial));


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
                    color = color.add(r.getColor(world, numBounces));
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