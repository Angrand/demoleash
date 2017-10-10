package GameState;

import LevelMaps.Background;
import Main.MainBoard;
import Objects.Bullet;
import Objects.Enemies.DumbMelee;
import Objects.Enemies.Enemy;
import Objects.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Level1State extends GameState {

    private boolean firingLeft;
    private boolean firingRight;
    private boolean firingUp;
    private boolean firingDown;

    private final long FIRING_DELAY = 80_000_000;
    private long startFiring;
    private long startUpdate;

    private Player myPlayer;
    private Background bg;
    private ArrayList<Bullet> bullets;
    private ArrayList<Enemy> enemies;
    private int bulletSpeed = 8;

    private Font stringFont = new Font("Arial", Font.BOLD, 20);
    private int sector;

    public Level1State(GameStateManager gameStateManager) {
        gsm = gameStateManager;
        init();
    }

    @Override
    public void init() {
        bg = new Background("/Backgrounds/level1bg2.jpg", 1, 0, 1);
        myPlayer = new Player(800, 800, 5, 0);
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        firingLeft = false;
        firingRight = false;
        firingDown = false;
        firingUp = false;
        startUpdate = 0;
        startFiring = 0;
    }

    @Override
    public void update() {
        startUpdate = System.nanoTime();

        /*-----------Firing-------*/

        if (firingLeft && startUpdate - startFiring > FIRING_DELAY) {
            startFiring = System.nanoTime();
            bullets.add(new Bullet(myPlayer.getObjectsX(),
                    myPlayer.getObjectsY() + myPlayer.getPlayerR() / 2 + 5,
                    bulletSpeed, 180,
                    -90));
        }
        if (firingRight && startUpdate - startFiring > FIRING_DELAY) {
            startFiring = System.nanoTime();
            bullets.add(new Bullet(myPlayer.getObjectsX() + myPlayer.getPlayerR(),
                    myPlayer.getObjectsY() + myPlayer.getPlayerR() / 2 + 5,
                    bulletSpeed, 0,
                    90));
        }
        if (firingUp && startUpdate - startFiring > FIRING_DELAY) {
            startFiring = System.nanoTime();
            bullets.add(new Bullet(myPlayer.getObjectsX() + myPlayer.getPlayerR() / 2,
                    myPlayer.getObjectsY(),
                    bulletSpeed, 90,
                    0));
        }
        if (firingDown && startUpdate - startFiring > FIRING_DELAY) {
            startFiring = System.nanoTime();
            bullets.add(new Bullet(myPlayer.getObjectsX() + myPlayer.getPlayerR() / 2,
                    myPlayer.getObjectsY() + myPlayer.getPlayerR() + 5,
                    bulletSpeed, -90,
                    -180));
        }
        /*------------------------*/

        spawnEnemies(1, 5);
        bg.update();
        myPlayer.update();
        updateEnemies(enemies);
        updateBullets(bullets);
    }

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
        myPlayer.draw(g);
        drawBullets(bullets, g);
        drawEnemies(enemies, g);



        /* String to print-----*/

        g.setColor(Color.BLACK);
        g.setFont(stringFont);
        g.drawString("bullets: " + bullets.size(), 40, 40);
        g.drawString("enemies: " + enemies.size(), 40, 60);
        for (int i = 0; i < enemies.size(); i++) {
            g.drawString("enemy #" + (i + 1) + ": " + enemies.get(i).getObjectsAngle(), 40, 80 + i * 20);
        }
        g.drawString("sector: " + sector, 40, 20);

        /*---------------------*/
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

        if (k == KeyEvent.VK_LEFT) {
            firingLeft = true;
        }
        if (k == KeyEvent.VK_UP) {
            firingUp = true;
        }
        if (k == KeyEvent.VK_RIGHT) {
            firingRight = true;
        }
        if (k == KeyEvent.VK_DOWN) {
            firingDown = true;
        }
        myPlayer.keyPressed(k);
    }

    @Override
    public void keyReleased(int k) {
        if (k == KeyEvent.VK_LEFT) {
            firingLeft = false;
        }
        if (k == KeyEvent.VK_UP) {
            firingUp = false;
        }
        if (k == KeyEvent.VK_RIGHT) {
            firingRight = false;
        }
        if (k == KeyEvent.VK_DOWN) {
            firingDown = false;
        }

        myPlayer.keyReleased(k);
    }

    /* private section ---------------------*/


    private void updateBullets(List<Bullet> bullets) {
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update();
            if (!bullets.get(i).isInside()) {
                bullets.remove(i);
                i--;
                continue;
            }

            for (int j = 0; j < enemies.size(); j++) {
                if (bullets.get(i).getCollRectangle1().intersects(enemies.get(j).getCollRectangle())) {
                    enemies.get(j).setHit(true);
                    bullets.remove(i);
                    i--;
                    break;
                }
            }

        }
    }

    private void drawBullets(List<Bullet> bullets, Graphics2D g) {
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
    }

    private void spawnEnemies(int type, int amount) {
        while(enemies.size() < 30) {
            int spawnX = 0;
            int spawnY = 0;

            Random random1 = new Random(System.nanoTime());
            sector = random1.nextInt(4) + 1;


            switch (sector) {
                case 1:
                    spawnX = (int) (-50 + MainBoard.BOARD_WIDTH - 200 * Math.random());
                    spawnY = (int) (50 + (MainBoard.BOARD_HEIGHT - 100) * Math.random());
                    break;
                case 2:
                    spawnX = (int) (50 + (MainBoard.BOARD_WIDTH - 100) * Math.random());
                    spawnY = (int) (50 + 80 * Math.random());
                    break;
                case 3:
                    spawnX = (int) (50 + 200 * Math.random());
                    spawnY = (int) (50 + (MainBoard.BOARD_HEIGHT - 100) * Math.random());
                    break;
                case 4:
                    spawnX = (int) (-50 + (MainBoard.BOARD_WIDTH - 200) * Math.random());
                    spawnY = (int) (-50 + MainBoard.BOARD_HEIGHT - 80 * Math.random());
                    break;
                default:
                    break;
            }

            switch (type) {
                case 1:
                    enemies.add(new DumbMelee(spawnX, spawnY, 5,
                            (int) System.nanoTime() * enemies.size() % 360));
                    break;
                default:
                    break;
            }

        }
    }

    private void drawEnemies(List<Enemy> enemies, Graphics2D g) {
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }
    }

    private void updateEnemies(List<Enemy> enemies) {
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
            if (enemies.get(i).isHit()) {
                enemies.remove(i);
                i--;
            }
        }
    }


}
