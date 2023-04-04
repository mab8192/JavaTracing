package com.mab8192.rt;

public abstract class Hittable {
    public Material material;

    public abstract Hit hit(Ray r, double t_min, double t_max);
}
