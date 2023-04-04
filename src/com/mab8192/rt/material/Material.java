package com.mab8192.rt.material;

import com.mab8192.rt.Hit;
import com.mab8192.rt.Ray;
import com.mab8192.rt.ScatteredRay;

public abstract class Material {
    public abstract ScatteredRay scatter(Ray r, Hit hit);
}
