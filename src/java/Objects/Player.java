package Objects;

import Main.Main;
import Main.MainBoard;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class Player extends GameObject{
    private int playerR = 30;
    private boolean isLeft;
    private boolean isRight;
    private boolean isUp;
    private boolean isDown;
    private int degrees = 45;
    private Shape collRectangle;


    private Color insideColor = new Color(100,100,200);


    public Player(int player_X, int player_Y, int playerXSpeed, int playerYSpeed) {
        super(player_X, player_Y, playerXSpeed, playerYSpeed);
    }

    @Override
    public void update() {
        if (isLeft) {
            if (getObjectsX() > 0) {
                setObjectsX(getObjectsX() - getObjectsXSpeed());
            }
        }
        if (isRight) {
            if (getObjectsX() < MainBoard.BOARD_WIDTH - playerR) {
                setObjectsX(getObjectsX() + getObjectsXSpeed());
            }
        }
        if (isUp) {
            if (getObjectsY() > 0) {
                setObjectsY(getObjectsY() - getObjectsYSpeed());
            }
        }
        if (isDown) {
            if (getObjectsY() < MainBoard.BOARD_HEIGHT - playerR) {
                setObjectsY(getObjectsY() + getObjectsYSpeed());
            }
        }
        collRectangle = new Rectangle((int) (getObjectsX()-0.3*playerR+3), (int) (getObjectsY() - 0.3*playerR+3),
                (int)(1.4*playerR), (int) (1.4*playerR));
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(200,150,120, 50));
        g.fill(collRectangle);

        Shape rect = new Rectangle(getObjectsX(), getObjectsY(), playerR, playerR);
        /** rotate here */
        g.rotate(Math.toRadians(degrees), getObjectsX() + playerR/2, getObjectsY() + playerR/2);
        g.setColor(insideColor);
        g.fill(rect);
        g.setStroke(new BasicStroke(6));
        g.setColor(insideColor.darker());
        g.draw(rect);
        g.setStroke(new BasicStroke(1));
        g.rotate(Math.toRadians(-degrees), getObjectsX() + playerR/2, getObjectsY() + playerR/2);
        //------------------------------
        degrees -= 2;

    }

    public int getPlayerR() {
        return playerR;
    }

    public void setLeft(boolean left) {
        isLeft = left;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    public void setUp(boolean up) {
        isUp = up;
    }

    public void setDown(boolean down) {
        isDown = down;
    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_LEFT) {
            setLeft(true);
        }
        if (k == KeyEvent.VK_RIGHT) {
            setRight(true);
        }
        if (k == KeyEvent.VK_UP) {
            setUp(true);
        }
        if (k == KeyEvent.VK_DOWN) {
            setDown(true);
        }
    }

    public void keyReleased(int k) {
        if (k == KeyEvent.VK_LEFT) {
            setLeft(false);
        }
        if (k == KeyEvent.VK_RIGHT) {
            setRight(false);
        }
        if (k == KeyEvent.VK_UP) {
            setUp(false);
        }
        if (k == KeyEvent.VK_DOWN) {
            setDown(false);
        }
    }
}
