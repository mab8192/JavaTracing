package com.mab8192.rt;

public class ScatteredRay {
    public boolean valid = false;
    public Ray ray;
    public Vec3 attenuation;

    public ScatteredRay(Ray ray, Vec3 attenuation) {
        this.ray = ray;
        this.attenuation = attenuation;
    }
}
