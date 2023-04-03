package com.mab8192.rt;

public class Sphere implements Hittable{
    private Vec3 center;
    private double radius;

    public Sphere() {
        this.center = new Vec3();
        this.radius = 1.0;
    }

    public Sphere(Vec3 center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public Hit hit(Ray r, double t_min, double t_max) {
        Vec3 oc = r.origin.sub(this.center);
        double a = r.dir.magnitude_sq();
        double half_b = oc.dot(r.dir);
        double c = oc.magnitude_sq() - radius*radius;
        double disc = half_b*half_b - a*c;
        if (disc < 0) return new Hit();

        double sqrtd = Math.sqrt(disc);

        double root = (-half_b - sqrtd) / a;

        if (root < t_min || t_max < root) {
            root = (-half_b + sqrtd) / a;
            if (root < t_min || t_max < root) {
                return new Hit();
            }
        }

        Hit hit = new Hit();
        hit.hit = true;
        hit.t = root;
        hit.p = r.at(hit.t);
        Vec3 outwardNormal = hit.p.sub(this.center).div(this.radius);
        hit.setFaceNormal(r, outwardNormal);

        return hit;
    }
}
