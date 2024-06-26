package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Entity {
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public Vector position;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle trueHitBox;
    public boolean collisionUD = false;
    public boolean collisionLR = false;
    protected int solidAreaDefaultX, solidAreaDefaultY;

    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }
}
