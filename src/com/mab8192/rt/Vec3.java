package com.mab8192.rt;

public class Vec3 {
    public double x, y, z;

    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vec3(Vec3 other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    public double magnitude_sq() {
        return x * x + y * y + z * z;
    }

    public double magnitude() {
        return Math.sqrt(this.magnitude_sq());
    }

    public Vec3 normalize() {
        double mag = this.magnitude();
        return new Vec3(x/mag, y/mag, z/mag);
    }

    public Vec3 add(Vec3 other) {
        return new Vec3(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public Vec3 sub(Vec3 other) {
        return new Vec3(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    public Vec3 mul(Vec3 other) {
        return new Vec3(this.x * other.x, this.y * other.y, this.z * other.z);
    }

    public Vec3 mul(double v) {
        return new Vec3(this.x * v, this.y * v, this.z * v);
    }

    public Vec3 div(Vec3 other) {
        return new Vec3(this.x / other.x, this.y / other.y, this.z / other.z);
    }

    public Vec3 div(double v) {
        return new Vec3(this.x / v, this.y / v, this.z / v);
    }

    public double dot(Vec3 other) {
        return this.x*other.x + this.y*other.y + this.z*other.z;
    }

    public Vec3 cross(Vec3 other) {
        return new Vec3(
                this.y*other.z - this.z*other.y,
                this.z*other.x - this.x*other.z,
                this.x*other.y - this.y*other.x
        );
    }

    public boolean nearZero() {
        double s = 1e-8;
        return (Math.abs(x) < s && Math.abs(y) < s && Math.abs(z) < s);
    }

    public String toString() {
        return String.format("%f %f %f", x, y, z);
    }

    public static Vec3 reflect(Vec3 v, Vec3 n) {
        // v - 2*dot(v,n)*n;
        return v.sub(n.mul(2*v.dot(n)));
    }

    public static Vec3 refract(Vec3 v, Vec3 n, double eta_ratio) {
        double cos_theta = Math.min(v.mul(-1).dot(n), 1.0);
        Vec3 r_perp = v.add(n.mul(cos_theta)).mul(eta_ratio);
        Vec3 r_parallel = n.mul(-Math.sqrt(Math.abs(1.0 - r_perp.magnitude_sq())));

        return r_perp.add(r_parallel);
    }

    public static Vec3 random() {
        return new Vec3(Utils.randomDouble(), Utils.randomDouble(), Utils.randomDouble());
    }

    public static Vec3 random(double min, double max) {
        return new Vec3(Utils.randomDouble(min, max), Utils.randomDouble(min, max), Utils.randomDouble(min, max));
    }

    public static Vec3 randomInUnitSphere() {
        while (true) {
            Vec3 p = Vec3.random(-1, 1);
            if (p.magnitude_sq() >= 1) continue;
            return p;
        }
    }

    public static Vec3 randomUnitVector() {
        return randomInUnitSphere().normalize();
    }

    public static Vec3 randomInHemisphere(Vec3 normal) {
        Vec3 unit = randomUnitVector();
        if (unit.dot(normal) > 0) {
            return unit;
        } else {
            return unit.mul(-1);
        }
    }
}
