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
        this.x /= mag;
        this.y /= mag;
        this.z /= mag;
        return this;
    }

    public Vec3 add(Vec3 other) {
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;
        return this;
    }

    public Vec3 sub(Vec3 other) {
        this.x -= other.x;
        this.y -= other.y;
        this.z -= other.z;
        return this;
    }

    public Vec3 mul(Vec3 other) {
        this.x *= other.x;
        this.y *= other.y;
        this.z *= other.z;
        return this;
    }

    public Vec3 mul(double v) {
        this.x *= v;
        this.y *= v;
        this.z *= v;
        return this;
    }

    public Vec3 div(Vec3 other) {
        this.x /= other.x;
        this.y /= other.y;
        this.z /= other.z;
        return this;
    }

    public Vec3 div(double v) {
        this.x /= v;
        this.y /= v;
        this.z /= v;
        return this;
    }

    public String toString() {
        return String.format("%d %d %d", x, y, z);
    }
}
