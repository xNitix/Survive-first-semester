package object;

import entity.Entity;
import entity.Player;
import entity.Vector;
import mian.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Key extends AObject {

    public OBJ_Key() {
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public OBJ_Key(Vector position, GamePanel gp) {
        super(gp);
        name = "Key";
        this.position = position;
        solidArea.y = 5;
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean interaction(Entity entity) {
        if(entity instanceof Player player) {
            player.increaseKeyNumber();
            gp.playSE(2);
            System.out.println(STR."Keys: \{player.getKeyNumber()}");
            gp.getUi().showMessage("You found a key!");
        }
        return true;
    }
}
