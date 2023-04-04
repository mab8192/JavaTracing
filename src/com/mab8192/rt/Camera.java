package com.mab8192.rt;

public class Camera {
    private Vec3 origin;
    private Vec3 lowerLeftCorner;
    private Vec3 horizontal;
    private Vec3 vertical;

    public Camera(Vec3 lookFrom, Vec3 lookAt, Vec3 vUp, double vFOV, double aspectRatio) {
        double theta = Utils.degreesToRadians(vFOV);
        double h = Math.tan(theta/2);

        double viewportHeight = 2.0*h;
        double viewportWidth = aspectRatio * viewportHeight;

        Vec3 w = lookFrom.sub(lookAt).normalize();
        Vec3 u = vUp.cross(w).normalize();
        Vec3 v = w.cross(u);

        origin = lookFrom;
        horizontal = u.mul(viewportWidth);
        vertical = v.mul(viewportHeight);
        lowerLeftCorner = origin.sub(horizontal.div(2)).sub(vertical.div(2)).sub(w);
    }

    public Ray getRay(double s, double t) {
        return new Ray(origin, lowerLeftCorner.add(horizontal.mul(s)).add(vertical.mul(t)).sub(origin));
    }
}
