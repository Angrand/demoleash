package Objects.Enemies;

import Objects.GameObject;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Enemy extends GameObject {
    private int enemyHealth;
    private boolean isHit;
    private Rectangle2D collRectangle;

    public Enemy(int object_X, int object_Y, int objectsXSpeed, int objectsAngle) {
        super(object_X, object_Y, objectsXSpeed, objectsAngle);
        setHit(false);
    }

    public Rectangle2D getCollRectangle() {
        return collRectangle;
    }

    public void setCollRectangle(Rectangle2D collRectangle) {
        this.collRectangle = collRectangle;
    }

    public int getEnemyHealth() {
        return enemyHealth;
    }

    public void setEnemyHealth(int enemyHealth) {
        this.enemyHealth = enemyHealth;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    @Override
    public abstract void update();

    @Override
    public abstract void draw(Graphics2D g);
}
