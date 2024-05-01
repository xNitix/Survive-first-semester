package entity;

import static java.lang.Math.sqrt;

public class Vector {
    private int x, y;

    public Vector(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void vectorAdd(int x, int y, int speed){
        double[] result = normalizedVector(x,y);
        this.x += (int) Math.round(result[0] * speed);
        this.y += (int) Math.round(result[1] * speed);
    }

    private double[] normalizedVector(int x, int y){
        double denominator = sqrt(x*x + y*y);
        return new double[]{x/denominator, y/denominator};
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
