package Objects;

import Main.MainBoard;

import java.awt.*;

public class Bullet extends GameObject {
    private Color bulletColor = new Color(100, 10, 255);
    private int bulletR = 4;

    public Bullet(int object_X, int object_Y, int objectsXSpeed, int objectsYSpeed) {
        super(object_X, object_Y, objectsXSpeed, objectsYSpeed);
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
        g.setColor(bulletColor);
        g. fillPolygon(new int[] {getObjectsX() - bulletR, getObjectsX(), getObjectsX() + bulletR},
                new int[] {getObjectsY(), getObjectsY() - bulletR * 2, getObjectsY()}, 3);

        g.setStroke(new BasicStroke(3));
        g.setColor(bulletColor.darker());
        g. drawPolygon(new int[] {getObjectsX() - bulletR, getObjectsX(), getObjectsX() + bulletR},
                new int[] {getObjectsY(), getObjectsY() - bulletR * 2, getObjectsY()}, 3);

        g.setStroke(new BasicStroke(1));
    }

}
