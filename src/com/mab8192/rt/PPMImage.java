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
        int r = (int)(color.x*255.999/samplesPerPixel);
        int g = (int)(color.y*255.999/samplesPerPixel);
        int b = (int)(color.z*255.999/samplesPerPixel);
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
