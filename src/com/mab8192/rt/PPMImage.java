package com.mab8192.rt;

import java.io.FileWriter;
import java.io.IOException;

public class PPMImage {
    private final StringBuffer contents;

    public PPMImage(int width, int height) {
        this.contents = new StringBuffer();
        this.contents.append("P3\n").append(width).append(" ").append(height).append("\n255\n");
    }

    public void writeColor(Vec3 color, int samplesPerPixel) {
        // the scale and sqrt are for gamma correction (gamma 2)
        double scale = 1.0/samplesPerPixel;
        int r = (int)(Math.sqrt(scale * color.x)*255.999);
        int g = (int)(Math.sqrt(scale * color.y)*255.999);
        int b = (int)(Math.sqrt(scale * color.z)*255.999);

        // gamma correction
        this.contents.append(String.format("%d %d %d\n", r, g, b));
    }

    public void save(String path) throws IOException {
        FileWriter writer = new FileWriter(path);
        writer.write(this.getContents());
        writer.close();
    }

    public String getContents() {
        return this.contents.toString();
    }
}
