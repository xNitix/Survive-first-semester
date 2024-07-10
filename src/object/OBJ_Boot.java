package object;

import entity.Entity;
import entity.Player;
import entity.Vector;
import mian.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Boot extends AObject {

    public OBJ_Boot(Vector position, GamePanel gp) {
        super(gp);
        name = "Boot";
        this.position = position;
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/boot.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean interaction(Entity entity) {
        entity.increaseEntitySpeed(3);
        gamePanel.playSE(1);
        return true;
    }
}
