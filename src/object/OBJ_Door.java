package object;

import entity.Vector;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends AObject{

    public OBJ_Door(Vector position) {
        name = "Door";
        this.position = position;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        collison = true;
    }

}
