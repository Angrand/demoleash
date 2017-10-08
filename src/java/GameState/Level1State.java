package GameState;

import LevelMaps.Background;
import LevelMaps.LevelMap;
import Main.MainBoard;
import Objects.Player;

import java.awt.*;
import java.awt.event.KeyEvent;


public class Level1State extends GameState {
    Player myPlayer;
    Background bg;


    public Level1State(GameStateManager gameStateManager) {
        gsm = gameStateManager;
        init();
    }

    @Override
    public void init() {
        bg = new Background("/Backgrounds/level1bg2.jpg", 1);
        bg.setVector(0,1);
        myPlayer = new Player(800, 800, 5, 5);
    }

    @Override
    public void update() {
        bg.update();
        myPlayer.update();
    }

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
        myPlayer.draw(g);
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        myPlayer.keyPressed(k);
    }

    @Override
    public void keyReleased(int k) {
        myPlayer.keyReleased(k);
    }
}
