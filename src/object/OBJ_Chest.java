package object;

import entity.Vector;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends AObject{

    public OBJ_Chest(Vector position) {
        name = "chest";
        this.position = position;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
