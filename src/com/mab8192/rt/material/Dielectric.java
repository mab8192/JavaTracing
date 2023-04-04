package com.mab8192.rt.material;

import com.mab8192.rt.*;

public class Dielectric extends Material{
    private double ir;

    public Dielectric(double indexOfRefraction) {
        this.ir = indexOfRefraction;
    }

    @Override
    public ScatteredRay scatter(Ray r, Hit hit) {
        // Attenuation is 1: Glass doesn't absorb any light
        Vec3 attenuation = new Vec3(1, 1, 1);

        // assume one of the materials is always air with ir=1.0
        double refractionRatio = hit.frontFace ? (1.0/ir) : ir;

        Vec3 unit = r.dir.normalize();
        double cosTheta = Math.min(unit.mul(-1).dot(hit.normal), 1.0);
        double sinTheta = Math.sqrt(1 - cosTheta*cosTheta);

        boolean canRefract = refractionRatio * sinTheta <= 1.0;

        Vec3 scatterDir;

        if (canRefract && reflectance(cosTheta, refractionRatio) <= Utils.randomDouble()) {
            scatterDir = Vec3.refract(unit, hit.normal, refractionRatio);
        } else {
            scatterDir = Vec3.reflect(unit, hit.normal);
        }

        Ray scatteredRay = new Ray(hit.p, scatterDir);

        ScatteredRay result = new ScatteredRay(scatteredRay, attenuation);
        result.valid = true;

        return result;
    }

    private double reflectance(double cos, double ref_ratio) {
        // Schlicks approximation
        double r0 = (1 - ref_ratio) / (1 + ref_ratio);
        r0 *= r0;
        return r0 + (1-r0)*Math.pow((1-cos), 5);
    }
}
