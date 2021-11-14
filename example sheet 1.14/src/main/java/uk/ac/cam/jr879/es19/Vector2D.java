package uk.ac.cam.jr879.es19;

import java.util.Arrays;

class Vector2D {

    public float[] contents;
    public int length;

    Vector2D(float[] contents) {
        this.contents = contents;
        this.length = contents.length;
    }

    public void add(Vector2D v) {
        assert v.length == this.length;
        for (int i = 0; i < v.length; i++) {
            this.contents[i] += v.contents[i];
        }
    }

    public float scalarProd(Vector2D v) {
        assert v.length == this.length;
        float total = 0;
        for (int i = 0; i < v.length; i++) {
            total += this.contents[i] * v.contents[i];
        }
        return total;
    }

    public float magnitude() {
        float total = 0;
        for (float i: contents) {
            total += i*i;
        }
        return (float)Math.pow(total, 0.5);
    }

    public void normalise() {
        float mag = this.magnitude();
        for (int i = 0; i < this.length; i++) {
            this.contents[i] /= mag;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(contents);
    }
}
