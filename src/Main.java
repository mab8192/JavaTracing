import com.mab8192.rt.*;
import com.mab8192.rt.material.Dielectric;
import com.mab8192.rt.material.Lambertian;
import com.mab8192.rt.material.Material;
import com.mab8192.rt.material.Metal;

import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Window
        double aspectRatio = 3.0/2.0;
        int width = 1200;
        int height = (int) (width / aspectRatio);

        // Ray tracing settings
        int samplesPerPixel = 500;
        int numBounces = 50;

        // Camera
        Vec3 lookFrom = new Vec3(13, 2, 3);
        Vec3 lookAt = new Vec3(0, 0, 0);
        Vec3 vUp = new Vec3(0, 1, 0);
        double fov = 20;
        double distToFocus = 10.0;
        double aperture = 0.1;
        Camera camera = new Camera(lookFrom, lookAt, vUp, fov, aspectRatio, aperture, distToFocus);

        // World
        Scene world = Scene.randomScene();

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