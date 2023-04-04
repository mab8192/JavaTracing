package com.mab8192.rt.material;

import com.mab8192.rt.Hit;
import com.mab8192.rt.Ray;
import com.mab8192.rt.ScatteredRay;
import com.mab8192.rt.Vec3;

public class Metal extends Material{
    public Vec3 albedo;
    public double fuzz;

    public Metal(Vec3 albedo, double f) {
        this.albedo = albedo;
        this.fuzz = f < 1 ? f : 1;
    }

    @Override
    public ScatteredRay scatter(Ray r, Hit hit) {
        Vec3 reflectedDir = Vec3.reflect(r.dir.normalize(), hit.normal);
        Ray scattered = new Ray(hit.p, reflectedDir.add(Vec3.randomInUnitSphere().mul(fuzz)));
        Vec3 attenuation = albedo;

        ScatteredRay result = new ScatteredRay(scattered, attenuation);
        result.valid = scattered.dir.dot(hit.normal) > 0;

        return result;
    }
}
