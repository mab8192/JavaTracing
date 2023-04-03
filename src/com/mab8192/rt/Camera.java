package com.mab8192.rt;

public class Camera {
    private Vec3 origin;
    private Vec3 lowerLeftCorner;
    private Vec3 horizontal;
    private Vec3 vertical;

    public Camera(double aspectRatio) {
        double viewportHeight = 2.0;
        double viewportWidth = aspectRatio * viewportHeight;
        double focal_length = 1.0;

        origin = new Vec3();
        horizontal = new Vec3(viewportWidth, 0, 0);
        vertical = new Vec3(0, viewportHeight, 0);
        lowerLeftCorner = origin.sub(horizontal.div(2)).sub(vertical.div(2)).sub(new Vec3(0, 0, focal_length));
    }

    public Ray getRay(double u, double v) {
        return new Ray(origin, lowerLeftCorner.add(horizontal.mul(u)).add(vertical.mul(v)).sub(origin));
    }
}
