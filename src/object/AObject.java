package object;

import entity.Entity;
import entity.Vector;
import interfaces.drawable;
import mian.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class AObject implements drawable {
    protected BufferedImage image;
    protected String name;
    protected boolean collison = false;
    protected Vector position;
    protected GamePanel gp;

    protected Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    protected int solidAreaDefaultX = 0;
    protected int solidAreaDefaultY = 0;

    public AObject() {

    }

    public AObject(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void draw(Graphics2D g2) {
        int screenX = position.getX() - gp.player.getPosition().getX() + gp.player.getCameraPosition().getX();
        int screenY = position.getY() - gp.player.getPosition().getY() + gp.player.getCameraPosition().getY();

        if (position.getX() + gp.TILE_SIZE > gp.player.getPosition().getX() - gp.player.getCameraPosition().getX() &&
                (position.getX() - gp.TILE_SIZE < gp.player.getPosition().getX() + gp.player.getCameraPosition().getX()) &&
                (position.getY() + gp.TILE_SIZE > gp.player.getPosition().getY() - gp.player.getCameraPosition().getY()) &&
                (position.getY() - gp.TILE_SIZE < gp.player.getPosition().getY() + gp.player.getCameraPosition().getY())) {
            g2.drawImage(image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
        }

    }

    public abstract boolean interaction(Entity entity);

    public BufferedImage getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public boolean isCollision() {
        return collison;
    }

    public Vector getPosition() {
        return position;
    }

    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }
}
