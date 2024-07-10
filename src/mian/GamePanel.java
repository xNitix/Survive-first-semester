package mian;

import entity.Player;
import object.AObject;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int START_TILE_SIZE = 16;
    final int SCALE = 3;
    public final int TILE_SIZE = START_TILE_SIZE*SCALE;
    public final int MAX_SCREEN_COLUM = 16;
    public final int MAX_SCREEN_ROW = 12;
    public final int SCREEN_WIDTH = MAX_SCREEN_COLUM * TILE_SIZE;
    public final int SCREEN_HEIGHT = MAX_SCREEN_ROW * TILE_SIZE;
    public final int MAX_WORLD_COL = 50;
    public final int MAX_WORLD_ROW = 50;
    final int FPS = 60;
    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    public Player player = new Player(this, keyH);
    private AObject[] objects = new AObject[10];

    private AssetSetter assetSetter = new AssetSetter(this);

    TileManager tileM = new TileManager(this);
    public CollisonChecker cChecker = new CollisonChecker(this);

    Sound sound = new Sound();

    public GamePanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // drawing from this component will be done in an offscreen painting buffer
        this.addKeyListener(keyH);
        this.setFocusable(true); // GamePanel can be "focused" to receive key input
    }

    public void setupGame(){
        assetSetter.setObject();
        playMusic(0);
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
        tileM.draw(g2);

        for (AObject object : objects) {
            if (object != null) {
                object.draw(g2, this);
            }
        }

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

    public AObject[] getObjects() {
        return objects;
    }

    public Object getObjectAt(int index) {
        return objects[index];
    }

    public void playMusic(int index) {
        this.sound.setFile(index);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        this.sound.stop();
    }

    public void playSE(int index) {
        sound.setFile(index);
        sound.play();
    }
}
