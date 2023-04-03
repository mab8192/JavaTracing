package com.mab8192.rt;

public interface Hittable {
    public Hit hit(Ray r, double t_min, double t_max);
}
