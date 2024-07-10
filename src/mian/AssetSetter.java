package mian;

import entity.Vector;
import object.*;

public class AssetSetter {
    private final GamePanel gp;

    public AssetSetter (GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        AObject[] objects = gp.getObjects();
        objects[0] = new OBJ_Key(new Vector(25*gp.TILE_SIZE,4*gp.TILE_SIZE), gp);
        objects[1] = new OBJ_Key(new Vector(25*gp.TILE_SIZE,40*gp.TILE_SIZE), gp);
        objects[2] = new OBJ_Chest(new Vector(37*gp.TILE_SIZE,41*gp.TILE_SIZE), gp);
        objects[3] = new OBJ_Door(new Vector(37*gp.TILE_SIZE,37*gp.TILE_SIZE), gp);
        objects[4] = new OBJ_Door(new Vector(38*gp.TILE_SIZE,37*gp.TILE_SIZE), gp);
        objects[5] = new OBJ_Boot(new Vector(40*gp.TILE_SIZE,23*gp.TILE_SIZE), gp);
    }
}
