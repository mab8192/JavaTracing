package com.mab8192.rt;

import com.mab8192.rt.material.Dielectric;
import com.mab8192.rt.material.Lambertian;
import com.mab8192.rt.material.Material;
import com.mab8192.rt.material.Metal;

import java.util.ArrayList;

public class Scene {
    private ArrayList<Hittable> hittables;

    public Scene() {
        hittables = new ArrayList<>();
    }

    public void add(Hittable hittable) {
        hittables.add(hittable);
    }

    public void clear() {
        hittables = new ArrayList<>();
    }

    public Hit hit(Ray r, double t_min, double t_max) {
        Hit closestHit = new Hit();
        double closestSoFar = t_max;

        for (Hittable obj : this.hittables) {
            Hit hit = obj.hit(r, t_min, closestSoFar);
            if (hit.didHit) {

                closestHit = hit;
                closestSoFar = hit.t;
            }
        }

        return closestHit;
    }

    public static Scene randomScene() {
        Scene scene = new Scene();

        Material groundMaterial = new Lambertian(new Vec3(0.5, 0.5, 0.5));
        scene.add(new Sphere(new Vec3(0, -1000, 0), 1000, groundMaterial));

        for (int a = -11; a <= 11; a++) {
            for (int b = -11; b <= 11; b++) {
                double choose_mat = Utils.randomDouble();
                Vec3 center = new Vec3(a + 0.9*Utils.randomDouble(), 0.2, b + 0.9*Utils.randomDouble());

                if (center.sub(new Vec3(4, 0.2, 0)).magnitude() > 0.9) {
                    Material mat;
                    if (choose_mat < 0.8) {
                        // diffuse material
                        mat = new Lambertian(Colors.randomColor());

                    } else if (choose_mat < 0.95) {
                        // metal
                        Vec3 albedo = Colors.randomColor();
                        double fuzz = Utils.randomDouble(0, 0.5);
                        mat = new Metal(albedo, fuzz);
                    } else {
                        // glass
                        mat = new Dielectric(1.5);
                    }

                    scene.add(new Sphere(center, 0.2, mat));
                }
            }
        }

        return scene;
    }
}
