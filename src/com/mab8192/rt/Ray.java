package com.mab8192.rt;

public class Ray {
    public final Vec3 origin, dir;

    public Ray(Vec3 origin, Vec3 dir) {
        this.origin = origin;
        this.dir = dir;
    }

    public Vec3 at(double t) {
        // origin + dir*t
        return new Vec3(origin.add(dir.mul(t)));
    }

    public Vec3 getColor() {
        // Return red if we hit a sphere
        double t = hitSphere(new Vec3(0, 0, -1), 0.5);
        if (t > 0) {
            Vec3 normal = this.at(t).sub(new Vec3(0, 0, -1)).normalize();
            return normal.add(new Vec3(1, 1, 1)).div(2);
        }
        // Otherwise return a white-blue gradient along the y-axis
        Vec3 unit = dir.normalize();
        t = 0.5*(unit.y + 1.0);
        Vec3 t1 = new Vec3(1, 1, 1);
        Vec3 t2 = new Vec3(0.5, 0.7, 1.0);

        return new Vec3(t1.mul(1.0-t).add(t2.mul(t)));
    }

    public double hitSphere(Vec3 center, double radius) {
        Vec3 oc = this.origin.sub(center);
        double a = dir.magnitude_sq();
        double half_b = oc.dot(dir);
        double c = oc.magnitude_sq() - radius*radius;
        double disc = half_b*half_b - a*c;
        if (disc < 0) {
            return -1.0;
        }
        return (-half_b - Math.sqrt(disc)) / a; // take the first solution since that will be the closer hit point
    }
}
