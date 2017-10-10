package Objects.Enemies;

import Main.MainBoard;

import java.awt.*;

public class DumbMelee extends Enemy {
    private int objectR = 50;


    public DumbMelee(int object_X, int object_Y, int objectsXSpeed, int objectsAngle) {
        super(object_X, object_Y, objectsXSpeed, objectsAngle);
    }

    @Override
    public void update() {
        if (getObjectsX()> 0 && getObjectsX() + objectR < MainBoard.BOARD_WIDTH) {
            setObjectsX(getObjectsX() + this.dx(getObjectsSpeed(), getObjectsAngle()));
        } else {
            setObjectsAngle(180 - getObjectsAngle());
            setObjectsX(getObjectsX() + this.dx(getObjectsSpeed(), getObjectsAngle()));
        }
        if (getObjectsY()> 0 && getObjectsY() + objectR < MainBoard.BOARD_HEIGHT) {
            setObjectsY(getObjectsY() + this.dy(getObjectsSpeed(), getObjectsAngle()));
        } else {
            setObjectsAngle(-getObjectsAngle());
            setObjectsY(getObjectsY() + this.dy(getObjectsSpeed(), getObjectsAngle()));
        }

        setCollRectangle(new Rectangle(getObjectsX(), getObjectsY(), objectR, objectR));
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(200, 70, 50, 230));
        g.fillOval(getObjectsX(), getObjectsY(), objectR, objectR);
        g.setStroke(new BasicStroke(6));
        g.setColor(new Color(150, 20, 0, 230));
        g.drawOval(getObjectsX(), getObjectsY(), objectR, objectR);
        g.setStroke(new BasicStroke(1));

        g.setColor(new Color(200,150,120, 0));
        g.fill(getCollRectangle());
    }
}
