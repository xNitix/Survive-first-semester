package object;

import entity.Entity;
import entity.Vector;
import mian.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Chest extends AObject{

    public OBJ_Chest(Vector position, GamePanel gp) {
        super(gp);
        name = "chest";
        this.position = position;
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean interaction(Entity entity) {
        gamePanel.playSE(1);
        return true;
    }
}
