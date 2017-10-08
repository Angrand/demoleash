package GameState;

import LevelMaps.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState {

    private Background bg;

    private int currentChoice = 0;
    private String[] options = {
            "Start",
            "Help",
            "Quit"
    };

    private Color titleColor;
    private Font titleFont;
    private Color fontColor;
    private Font font;

    public MenuState(GameStateManager gameStateManager) {
        gsm = gameStateManager;

        bg = new Background("/Backgrounds/menu4.jpg", 1);
        bg.setVector(0, 1);

        titleColor = new Color(180,150,100);
        titleFont = new Font("Century Gothic", Font.BOLD, 50);
        fontColor = new Color(80,70,100);
        font = new Font("Arial", Font.BOLD, 40);
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {
        bg.update();
    }

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);

        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("DemoLeash", 700, 200);

        g.setFont(font);
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.RED);
            }
            else {
                g.setColor(fontColor);
            }
            g.drawString(options[i], 745, 250+i*40);
        }
    }

    @Override
    public void keyPressed(int k) {
        if(k== KeyEvent.VK_ENTER) {
            select();
        }
        if(k== KeyEvent.VK_UP) {
            currentChoice--;
            if (currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }
        if(k== KeyEvent.VK_DOWN) {
            currentChoice++;
            if (currentChoice == options.length) {
                currentChoice = 0;
            }
        }

    }

    private void select() {
        if (currentChoice == 0) {
            //start
            gsm.setState(GameStateManager.LEVEL1STATE);
        }
        if (currentChoice == 1) {
            //help
        }
        if (currentChoice == 2) {
            //exit
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(int k) {

    }
}
