package mian;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int START_TILE_SIZE = 16;
    final int SCALE = 3;
    final int TILE_SIZE = START_TILE_SIZE*SCALE;
    final int MAX_SCREEN_COLUM = 16;
    final int MAX_SCREEN_ROW = 12;
    final int SCREEN_WIDTH = MAX_SCREEN_COLUM * TILE_SIZE;
    final int SCREEN_HEIGHT = MAX_SCREEN_ROW * TILE_SIZE;

    Thread gameThread;

    public GamePanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // drawing from this component will be done in an offscreen painting buffer
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while(gameThread != null){
            System.out.println("It's allive D: !!!");
        }
    }
}
