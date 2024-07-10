package object;

import entity.Entity;
import entity.Player;
import entity.Vector;
import mian.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Key extends AObject {

    public OBJ_Key(Vector position, GamePanel gp) {
        super(gp);
        name = "Key";
        this.position = position;
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
        solidArea.y = 5;
    }

    @Override
    public boolean interaction(Entity entity) {
        if(entity instanceof Player player) {
            player.increaseKeyNumber();
            gamePanel.playSE(2);
            System.out.println(STR."Keys: \{player.getKeyNumber()}");
        }
        return true;
    }
}
