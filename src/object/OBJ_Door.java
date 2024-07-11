package object;

import entity.Entity;
import entity.Player;
import entity.Vector;
import mian.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Door extends AObject{

    public OBJ_Door(Vector position, GamePanel gp) {
        super(gp);
        name = "Door";
        this.position = position;
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
        collison = true;
    }

    @Override
    public boolean interaction(Entity entity) {
        if(entity instanceof Player player) {
            if(player.getKeyNumber() > 0) {
                gp.playSE(1);
                player.decreaseKeyNumber();
                return true;
            } else {
                gp.getUi().showMessage("You need a key!");
            }
            System.out.println(STR."Keys: \{player.getKeyNumber()}");
        }
        return false;
    }
}
