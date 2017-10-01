package Main;

import GameState.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Scanner;

public class MainBoard extends JPanel implements Runnable, KeyListener {

    /**
     * DIMENSIONS
     */
    public static final int WIDTH = 320;
    public static final int HEIGHT = 240;
    public static final int SCALE = 2;

    private Thread thread;
    private boolean ifRunning;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;

    /**
     * IMAGE
     */
    private BufferedImage myImage;
    private Graphics2D g;

    /**
     * Game State Manager
     */
    private GameStateManager gsm;


    private double avgFPS;

    /**
     * Constructor
     */
    public MainBoard() throws HeadlessException {
        super();
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setFocusable(true);
        requestFocus();
    }

    /**
     * FUNCTIONS
     */
    @Override
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    /**
     * RUN
     */
    @Override
    public void run() {

        init();

        long startTime;
        long elapsedTime;
        long waitTime;

        /**GAME LOOP*/
        while (ifRunning) {
            startTime = System.nanoTime();

            mainBoardUpdate();
            mainBoardRender();
            mainBoardDrawToScreen();

            elapsedTime = System.nanoTime() - startTime;

            waitTime = targetTime - elapsedTime / 1_000_000;
            if (waitTime > 0) {
                try {
                    Thread.sleep(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void init() {
        myImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) myImage.getGraphics();
        ifRunning = true;

        gsm = new GameStateManager();
    }

    private void mainBoardUpdate() {
        gsm.update();
    }

    private void mainBoardRender() {
        gsm.draw(g);
    }

    private void mainBoardDrawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(myImage, 0, 0,
                WIDTH * SCALE, HEIGHT * SCALE,
                null);
        g2.dispose();
    }


    @Override
    public void keyTyped(KeyEvent key) {

    }

    @Override
    public void keyPressed(KeyEvent key) {
        gsm.keyPressed(key.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent key) {
        gsm.keyReleased(key.getKeyCode());
    }
}
