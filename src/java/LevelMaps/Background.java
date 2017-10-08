package LevelMaps;

import Main.MainBoard;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Background {

    private BufferedImage image;

    private double x;
    private double y;
    private double speed_dx;
    private double speed_dy;

    private double moveScale;

    public Background(String s, double moveScale, double dx, double dy) {
        speed_dx = dx;
        speed_dy = dy;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(s));
            this.moveScale = moveScale;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPosition(double x, double y) {
        this.x = (x * moveScale) % MainBoard.BOARD_WIDTH;
        this.y = (y * moveScale) % MainBoard.BOARD_HEIGHT;
    }

    public void update() {
        setPosition(x, y);
        x += speed_dx;
        y += speed_dy;
    }

    public void draw(Graphics2D g) {
        g.drawImage(image, (int) x, (int) y, null);
        if (x < 0) {
            g.drawImage(image, (int) x + MainBoard.BOARD_WIDTH, (int) y, null);
        }
        if (x > 0) {
            g.drawImage(image, (int) x - MainBoard.BOARD_WIDTH, (int) y, null);
        }
        if (y < 0) {
            g.drawImage(image, (int) x, (int) y + MainBoard.BOARD_HEIGHT, null);
        }
        if (y > 0) {
            g.drawImage(image, (int) x, (int) y - MainBoard.BOARD_HEIGHT, null);
        }
    }
}
