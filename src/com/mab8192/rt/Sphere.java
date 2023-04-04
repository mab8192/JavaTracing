package com.mab8192.rt;

public class Sphere extends Hittable{
    private Vec3 center;
    private double radius;

    public Sphere() {
        super();
        this.center = new Vec3();
        this.radius = 1.0;
        this.material = new Lambertian(Colors.WHITE);
    }

    public Sphere(Vec3 center, double radius) {
        this.center = center;
        this.radius = radius;
        this.material = new Lambertian(Colors.WHITE);
    }

    public Sphere(Vec3 center, double radius, Material mat) {
        this.center = center;
        this.radius = radius;
        this.material = mat;
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
        hit.didHit = true;
        hit.t = root;
        hit.p = r.at(hit.t);
        Vec3 outwardNormal = hit.p.sub(this.center).div(this.radius);
        hit.setFaceNormal(r, outwardNormal);
        hit.material = this.material;

        return hit;
    }
}
