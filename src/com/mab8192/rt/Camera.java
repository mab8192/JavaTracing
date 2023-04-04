package com.mab8192.rt;

public class Camera {
    private Vec3 origin;
    private Vec3 lowerLeftCorner;
    private Vec3 horizontal;
    private Vec3 vertical;
    private Vec3 u, v, w;
    double lensRadius;

    public Camera(Vec3 lookFrom, Vec3 lookAt, Vec3 vUp, double vFOV, double aspectRatio,
                  double aperture, double focusDist) {
        double theta = Utils.degreesToRadians(vFOV);
        double h = Math.tan(theta/2);
        double viewportHeight = 2.0*h;
        double viewportWidth = aspectRatio * viewportHeight;

        w = lookFrom.sub(lookAt).normalize();
        u = vUp.cross(w).normalize();
        v = w.cross(u);

        origin = lookFrom;
        horizontal = u.mul(viewportWidth).mul(focusDist);
        vertical = v.mul(viewportHeight).mul(focusDist);
        lowerLeftCorner = origin.sub(horizontal.div(2)).sub(vertical.div(2)).sub(w.mul(focusDist));

        lensRadius = aperture / 2;
    }

    public Ray getRay(double s, double t) {
        Vec3 rd = Vec3.randomInUnitDisk().mul(lensRadius);
        Vec3 offset = u.mul(rd.x).add(v.mul(rd.y));
        return new Ray(
                origin.add(offset),
                lowerLeftCorner.add(horizontal.mul(s)).add(vertical.mul(t)).sub(origin).sub(offset)
        );
    }
}
