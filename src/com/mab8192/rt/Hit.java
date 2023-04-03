package com.mab8192.rt;

public class Hit {
    public boolean hit = false;
    public Vec3 p;
    public Vec3 normal;
    double t;

    boolean frontFace;

    public void setFaceNormal(Ray r, Vec3 outwardNormal) {
        frontFace = r.dir.dot(outwardNormal) < 0;
        normal = frontFace ? outwardNormal : outwardNormal.mul(-1);
    }
}
