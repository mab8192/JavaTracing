package com.mab8192.rt;

import com.mab8192.rt.material.Material;

public class Hit {
    public boolean didHit = false;
    public Vec3 p;
    public Vec3 normal;
    public double t;
    public Material material;
    public boolean frontFace;

    public void setFaceNormal(Ray r, Vec3 outwardNormal) {
        frontFace = r.dir.dot(outwardNormal) < 0;
        normal = frontFace ? outwardNormal : outwardNormal.mul(-1);
    }
}
