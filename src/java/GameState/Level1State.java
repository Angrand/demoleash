package GameState;

import LevelMaps.Background;
import LevelMaps.LevelMap;
import Main.MainBoard;
import Objects.Bullet;
import Objects.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;


public class Level1State extends GameState {

    boolean firingLeft;
    boolean firingRight;
    boolean firingUp;
    boolean firingDown;

    long firingDelay = 80_000_000;
    long startFiring = 0;
    long startUpdate = 0;

    Player myPlayer;
    Background bg;
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();


    public Level1State(GameStateManager gameStateManager) {
        gsm = gameStateManager;
        init();
    }

    @Override
    public void init() {
        bg = new Background("/Backgrounds/level1bg2.jpg", 1);
        bg.setVector(0,1);
        myPlayer = new Player(800, 800, 5, 5);
        firingLeft = false;
        firingRight = false;
        firingDown = false;
        firingUp = false;
    }

    @Override
    public void update() {
        startUpdate = System.nanoTime();
        if (firingLeft && startUpdate - startFiring > firingDelay) {
            startFiring = System.nanoTime();
            bullets.add(new Bullet(myPlayer.getObjectsX(),
                    myPlayer.getObjectsY() + myPlayer.getPlayerR()/2 + 5, 8, 0, -90));
        }
        if (firingRight && startUpdate - startFiring > firingDelay) {
            startFiring = System.nanoTime();
            bullets.add(new Bullet(myPlayer.getObjectsX() + myPlayer.getPlayerR(),
                    myPlayer.getObjectsY() + myPlayer.getPlayerR()/2 + 5, -8, 0, 90));
        }
        if (firingUp && startUpdate - startFiring > firingDelay) {
            startFiring = System.nanoTime();
            bullets.add(new Bullet(myPlayer.getObjectsX() + myPlayer.getPlayerR()/2,
                    myPlayer.getObjectsY(), 0, 8, 0));
        }
        if (firingDown && startUpdate - startFiring > firingDelay) {
            startFiring = System.nanoTime();
            bullets.add(new Bullet(myPlayer.getObjectsX() + myPlayer.getPlayerR()/2,
                    myPlayer.getObjectsY() + myPlayer.getPlayerR() + 5, 0, -8, -180));
        }
        bg.update();
        myPlayer.update();
        update(bullets);
    }

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
        myPlayer.draw(g);
        draw(bullets, g);
        g.setColor(Color.BLACK);
        Font stringFont = new Font("Arial", Font.BOLD, 20);
        g.setFont(stringFont);
        g.drawString("bullets: " + bullets.size(), 40, 40);

    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

        if (k == KeyEvent.VK_A) {
            firingLeft = true;
        }
        if (k == KeyEvent.VK_W) {
            firingUp = true;
        }
        if (k == KeyEvent.VK_D) {
            firingRight = true;
        }
        if (k == KeyEvent.VK_S) {
            firingDown = true;
        }
        myPlayer.keyPressed(k);
    }

    @Override
    public void keyReleased(int k) {
        if (k == KeyEvent.VK_A) {
            firingLeft = false;
        }
        if (k == KeyEvent.VK_W) {
            firingUp = false;
        }
        if (k == KeyEvent.VK_D) {
            firingRight = false;
        }
        if (k == KeyEvent.VK_S) {
            firingDown = false;
        }

        myPlayer.keyReleased(k);
    }

    private void update(List<Bullet> bullets) {
        for (int i = 0; i<bullets.size(); i++) {
            bullets.get(i).update();
            if (!bullets.get(i).isInside()) {
                bullets.remove(i);
                i--;
            }
        }
    }

    private void draw(List<Bullet> bullets, Graphics2D g) {
        for (Bullet bullet: bullets) {
            bullet.draw(g);
        }
    }

}
