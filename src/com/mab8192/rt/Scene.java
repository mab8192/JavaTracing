package com.mab8192.rt;

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
            if (hit.hit) {

                closestHit = hit;
                closestSoFar = hit.t;
            }
        }

        return closestHit;
    }
}
