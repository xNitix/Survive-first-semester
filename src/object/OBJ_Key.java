package object;

import entity.Vector;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends AObject {

    public OBJ_Key(Vector position) {
        name = "Key";
        this.position = position;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        solidArea.y = 5;
    }
}
