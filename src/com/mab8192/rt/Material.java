package com.mab8192.rt;

public abstract class Material {
    public abstract ScatteredRay scatter(Ray r, Hit hit);
}
