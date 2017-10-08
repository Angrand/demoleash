package Objects;

import Main.MainBoard;

import java.awt.*;

public class Bullet extends GameObject {
    private Color bulletColor = new Color(100, 10, 255);
    private int bulletR = 4;
    private Shape bulletPoly;
    private int rotation;

    public Bullet(int object_X, int object_Y, int objectsXSpeed, int objectsYSpeed, int rotation) {
        super(object_X, object_Y, objectsXSpeed, objectsYSpeed);
        this.rotation = rotation;
    }

    @Override
    public void update() {
        setObjectsX(getObjectsX() - getObjectsXSpeed());
        setObjectsY(getObjectsY() - getObjectsYSpeed());
    }

    public boolean isInside() {
        if (this.getObjectsX() < 0 ||
                this.getObjectsX() > MainBoard.BOARD_WIDTH ||
                this.getObjectsY() < 0 ||
                this.getObjectsY() > MainBoard.BOARD_HEIGHT) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        bulletPoly = new Polygon(new int[] {getObjectsX() - bulletR, getObjectsX(), getObjectsX() + bulletR},
                new int[] {getObjectsY(), getObjectsY() - bulletR * 2, getObjectsY()}, 3);

        g.rotate(Math.toRadians(rotation), getObjectsX(), getObjectsY() - bulletR);
        g.setColor(bulletColor);
        g.fill(bulletPoly);

        g.setStroke(new BasicStroke(3));
        g.setColor(bulletColor.darker());
        g. draw(bulletPoly);
        g.rotate(Math.toRadians(-rotation), getObjectsX(), getObjectsY() - bulletR);

        g.setStroke(new BasicStroke(1));
    }

}
