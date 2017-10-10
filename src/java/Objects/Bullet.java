package Objects;

import Main.MainBoard;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Bullet extends GameObject {
    private Color bulletColor = new Color(100, 10, 255);
    private int bulletR = 4;
    private Shape bulletPoly;
    private int rotation;
    private Rectangle2D collRectangle1;
    private Rectangle2D collRectangle2;


    public Bullet(int object_X, int object_Y, int objectsSpeed, int objectsAngle, int rotation) {
        super(object_X, object_Y, objectsSpeed, objectsAngle);
        this.rotation = rotation;
    }

    @Override
    public void update() {
        setObjectsX(getObjectsX() + this.dx(getObjectsSpeed(), getObjectsAngle()));
        setObjectsY(getObjectsY() + this.dy(getObjectsSpeed(), getObjectsAngle()));

        collRectangle1 = new Rectangle(getObjectsX()- (bulletR+3), getObjectsY() - (bulletR),
                2*(bulletR+3), (bulletR+3));
        collRectangle2 = new Rectangle(getObjectsX()- (bulletR+3)/2, getObjectsY() - 2*(bulletR)-3,
                (bulletR+3), (bulletR+3));


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

    public Rectangle2D getCollRectangle1() {
        return collRectangle1;
    }

    public Rectangle2D getCollRectangle2() {
        return collRectangle2;
    }

    @Override
    public void draw(Graphics2D g) {



        bulletPoly = new Polygon(new int[]{getObjectsX() - bulletR, getObjectsX(), getObjectsX() + bulletR},
                new int[]{getObjectsY(), getObjectsY() - bulletR * 2, getObjectsY()}, 3);

        g.rotate(Math.toRadians(rotation), getObjectsX(), getObjectsY() - bulletR);
        g.setColor(bulletColor);
        g.fill(bulletPoly);
        g.setStroke(new BasicStroke(3));
        g.setColor(bulletColor.darker());
        g.draw(bulletPoly);
        g.setStroke(new BasicStroke(1));

        g.setColor(new Color(200,150,120, 0));
        g.fill(collRectangle1);
        g.fill(collRectangle2);

        g.rotate(Math.toRadians(-rotation), getObjectsX(), getObjectsY() - bulletR);





    }

}
