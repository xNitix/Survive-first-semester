package mian;

import javax.swing.JFrame;
public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("WI Adventure");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();// window will fit the preferred size nad layouts

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }

}