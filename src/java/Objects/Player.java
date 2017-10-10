package Objects;


import Main.MainBoard;
import java.awt.*;
import java.awt.event.KeyEvent;


public class Player extends GameObject{
    private int playerR = 30;
    private boolean isLeft;
    private boolean isRight;
    private boolean isUp;
    private boolean isDown;
    private int degrees = 45;
    private Shape collRectangle;
    private Color insideColor = new Color(100,100,200);


    public Player(int player_X, int player_Y, int playerSpeed, int playerAngle) {
        super(player_X, player_Y, playerSpeed, playerAngle);
    }

    @Override
    public void update() {
        /* Moving----------*/
        if (isLeft) {
            if (getObjectsX() > 0) {
                setObjectsX(getObjectsX() - getObjectsSpeed());
            }
        }
        if (isRight) {
            if (getObjectsX() < MainBoard.BOARD_WIDTH - playerR) {
                setObjectsX(getObjectsX() + getObjectsSpeed());
            }
        }
        if (isUp) {
            if (getObjectsY() > 0) {
                setObjectsY(getObjectsY() - getObjectsSpeed());
            }
        }
        if (isDown) {
            if (getObjectsY() < MainBoard.BOARD_HEIGHT - playerR) {
                setObjectsY(getObjectsY() + getObjectsSpeed());
            }
        }
        /*-----------------*/
        collRectangle = new Rectangle((int) (getObjectsX()-0.3*playerR+3), (int) (getObjectsY() - 0.3*playerR+3),
                (int)(1.4*playerR), (int) (1.4*playerR));
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(200,150,120, 0));
        g.fill(collRectangle);

        Shape rect = new Rectangle(getObjectsX(), getObjectsY(), playerR, playerR);
        /* rotate here---*/
        g.rotate(Math.toRadians(degrees), getObjectsX() + playerR / 2, getObjectsY() + playerR / 2);
        g.setColor(insideColor);
        g.fill(rect);
        g.setStroke(new BasicStroke(6));
        g.setColor(insideColor.darker());
        g.draw(rect);
        g.setStroke(new BasicStroke(1));
        g.rotate(Math.toRadians(-degrees), getObjectsX() + playerR/2, getObjectsY() + playerR/2);
        /*---------------*/

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
        if (k == KeyEvent.VK_A) {
            setLeft(true);
        }
        if (k == KeyEvent.VK_D) {
            setRight(true);
        }
        if (k == KeyEvent.VK_W) {
            setUp(true);
        }
        if (k == KeyEvent.VK_S) {
            setDown(true);
        }
    }

    public void keyReleased(int k) {
        if (k == KeyEvent.VK_A) {
            setLeft(false);
        }
        if (k == KeyEvent.VK_D) {
            setRight(false);
        }
        if (k == KeyEvent.VK_W) {
            setUp(false);
        }
        if (k == KeyEvent.VK_S) {
            setDown(false);
        }
    }
}
