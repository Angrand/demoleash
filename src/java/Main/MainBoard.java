package Main;

import GameState.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class MainBoard extends JPanel implements Runnable, KeyListener {

    /*
     * DIMENSIONS
     */
    public static final int BOARD_WIDTH = 1600;
    public static final int BOARD_HEIGHT = 900;
    public static final int BOARD_SCALE = 1;

    private Thread animator;
    private boolean isRunning;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;

    /*
     * IMAGE fields
     */
    private BufferedImage myImage;
    private Graphics2D g;

    /*
     * Game State Manager
     */
    private GameStateManager gsm;


    /*
     * Constructor
     */
    MainBoard() throws HeadlessException {
        super();
        setPreferredSize(new Dimension(BOARD_WIDTH * BOARD_SCALE, BOARD_HEIGHT * BOARD_SCALE));
        setFocusable(true);
        requestFocus();
    }

    /*
     * FUNCTIONS
     * addNotify comes at start, new Thread.
     */
    @Override
    public void addNotify() {
        super.addNotify();
        if (animator == null) {
            animator = new Thread(this);
            addKeyListener(this);
            animator.start();
        }
    }

    /*
     * RUN
     */
    @Override
    public void run() {

        init();

        long startTime;
        long elapsedTime;
        long waitTime;

        /*--GAME LOOP------*/
        while (isRunning) {
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
        /*------------------*/
    }

    private void init() {
        gsm = new GameStateManager();

        myImage = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) myImage.getGraphics();
        isRunning = true;

    }

    private void mainBoardUpdate() {
        /* gamesStateManager update*/
        gsm.update();
    }

    private void mainBoardRender() {
        /* gamesStateManager draw*/
        gsm.draw(g);
    }

    private void mainBoardDrawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(myImage, 0, 0,
                BOARD_WIDTH * BOARD_SCALE, BOARD_HEIGHT * BOARD_SCALE,
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
