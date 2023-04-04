import com.mab8192.rt.*;
import com.mab8192.rt.material.Dielectric;
import com.mab8192.rt.material.Lambertian;
import com.mab8192.rt.material.Material;
import com.mab8192.rt.material.Metal;

import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        double aspectRatio = 16.0/9.0;
        int width = 400;
        int height = (int) (width / aspectRatio);
        int samplesPerPixel = 100;
        int numBounces = 10;
        double fov = 40;

        // Camera
        Camera camera = new Camera(new Vec3(-2, 2, 1), new Vec3(0, 0, -1), new Vec3(0, 1, 0), fov, aspectRatio);

        // World
        Scene world = new Scene();

        Material groundMaterial = new Lambertian(new Vec3(0.4, 0.8, 0.0));
        Material centerMaterial = new Dielectric(1.5);
        Material leftMaterial = new Metal(new Vec3(0.8, 0.8, 0.8), 0.2);
        Material rightMaterial = new Metal(new Vec3(0.8, 0.6, 0.2), 0.1);

        world.add(new Sphere(new Vec3(0, -100.5, -1), 100, groundMaterial));
        world.add(new Sphere(new Vec3(0, 0, -1), -0.4, centerMaterial));
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