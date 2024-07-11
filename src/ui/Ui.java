package ui;

import interfaces.drawable;
import mian.GamePanel;
import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ui implements drawable {
    GamePanel gp;
    Font arial40;
    private boolean messageOn = false;
    private String message = "";

    private int messageTimer;

    BufferedImage keyImage;

    public Ui(GamePanel gp) {
        this.gp = gp;
        arial40 = new Font("Arial", Font.PLAIN, 35);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.getImage();
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setFont(arial40);
        g2.setColor(Color.white);
        g2.drawImage(keyImage, gp.TILE_SIZE / 2, gp.TILE_SIZE / 2, gp.TILE_SIZE, gp.TILE_SIZE, null);
        g2.drawString(STR." : \{gp.player.getKeyNumber()}", 70, 58);

        if (messageOn) {
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gp.TILE_SIZE / 2, gp.TILE_SIZE * 5);
            messageTimer++;
            if (messageTimer > 180) {
                messageTimer = 0;
                messageOn = false;
            }
        }
    }
}
