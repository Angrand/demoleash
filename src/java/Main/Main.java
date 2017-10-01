package Main;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        JFrame myFrame = new JFrame("DemoLeash");
        myFrame.setContentPane(new MainBoard());

        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setResizable(false);
        myFrame.pack();
        myFrame.setVisible(true);
    }
}
