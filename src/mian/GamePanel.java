package mian;

import entity.Player;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int START_TILE_SIZE = 16;
    final int SCALE = 3;
    public final int TILE_SIZE = START_TILE_SIZE*SCALE;
    final int MAX_SCREEN_COLUM = 16;
    final int MAX_SCREEN_ROW = 12;
    final int SCREEN_WIDTH = MAX_SCREEN_COLUM * TILE_SIZE;
    final int SCREEN_HEIGHT = MAX_SCREEN_ROW * TILE_SIZE;

    final int FPS = 60;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();

    Player player = new Player(this, keyH);

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // drawing from this component will be done in an offscreen painting buffer
        this.addKeyListener(keyH);
        this.setFocusable(true); // GamePanel can be "focused" to receive key input
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        player.draw(g2);
        g2.dispose();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 /FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta=0;
            }
        }
    }
}
