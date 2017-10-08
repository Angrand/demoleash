package Objects;

import java.awt.*;

public abstract class GameObject {
    private int objectsX;
    private int objectsY;
    private int objectsXSpeed;
    private int objectsYSpeed;


    public abstract void update();
    public abstract void draw(Graphics2D g);


    public GameObject(int object_X, int object_Y, int objectsXSpeed, int objectsYSpeed) {
        this.objectsX = object_X;
        this.objectsY = object_Y;
        this.objectsXSpeed = objectsXSpeed;
        this.objectsYSpeed = objectsYSpeed;
    }

    public int getObjectsXSpeed() {
        return objectsXSpeed;
    }

    public void setObjectsXSpeed(int objectsXSpeed) {
        this.objectsXSpeed = objectsXSpeed;
    }

    public int getObjectsYSpeed() {
        return objectsYSpeed;
    }

    public void setObjectsYSpeed(int objectsYSpeed) {
        this.objectsYSpeed = objectsYSpeed;
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
