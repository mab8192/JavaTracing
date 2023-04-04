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

    public Vec3 getColor(Scene scene, int depth) {
        if (depth <= 0) {
            return Colors.BLACK;
        }
        // Check if this ray hits and object in the scene
        Hit hit = scene.hit(this, 0.001, Utils.infinity);
        if (hit.didHit) {
            ScatteredRay sr = hit.material.scatter(this, hit);
            if (sr.valid) {
                return sr.attenuation.mul(sr.ray.getColor(scene, depth-1));
            }
            return Colors.BLACK;
        }

        // Otherwise return a white-blue gradient along the y-axis
        Vec3 unit = dir.normalize();
        double t = 0.5*(unit.y + 1.0);
        Vec3 t1 = new Vec3(1, 1, 1);
        Vec3 t2 = new Vec3(0.5, 0.7, 1.0);

        return new Vec3(t1.mul(1.0-t).add(t2.mul(t)));
    }
}
