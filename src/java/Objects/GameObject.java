package Objects;

import java.awt.*;

public abstract class GameObject {
    private int objectsX;
    private int objectsY;
    private int objectsSpeed;
    private int objectsAngle;


    public abstract void update();

    public abstract void draw(Graphics2D g);


    public GameObject(int object_X, int object_Y, int objectsXSpeed, int objectsAngle) {
        this.objectsX = object_X;
        this.objectsY = object_Y;
        this.objectsSpeed = objectsXSpeed;
        this.objectsAngle = objectsAngle;
    }

    public int dx(int speed, int angle) {
        double dX = Math.toRadians(angle);
        return (int) (speed * Math.cos(dX));
    }

    public int dy(int speed, int angle) {
        double dY = Math.toRadians((double)angle);
        return (int) (speed * Math.sin(-dY));
    }

    public int getObjectsSpeed() {
        return objectsSpeed;
    }

    public void setObjectsSpeed(int objectsSpeed) {
        this.objectsSpeed = objectsSpeed;
    }

    public int getObjectsAngle() {
        return objectsAngle;
    }

    public void setObjectsAngle(int objectsAngle) {
        this.objectsAngle = objectsAngle;
    }

    public int getObjectsX() {

        return objectsX;
    }

    public void setObjectsX(int objectsX) {
        this.objectsX = objectsX;
    }

    public int getObjectsY() {
        return objectsY;
    }

    public void setObjectsY(int objectsY) {
        this.objectsY = objectsY;
    }
}
