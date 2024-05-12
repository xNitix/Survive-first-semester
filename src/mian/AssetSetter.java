package mian;

import entity.Vector;
import object.AObject;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {
    private final GamePanel gp;

    public AssetSetter (GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        AObject[] objects = gp.getObjects();
        objects[0] = new OBJ_Key(new Vector(25*gp.TILE_SIZE,4*gp.TILE_SIZE));
        objects[1] = new OBJ_Key(new Vector(25*gp.TILE_SIZE,40*gp.TILE_SIZE));
        objects[2] = new OBJ_Chest(new Vector(37*gp.TILE_SIZE,41*gp.TILE_SIZE));
        objects[3] = new OBJ_Door(new Vector(37*gp.TILE_SIZE,37*gp.TILE_SIZE));
        objects[4] = new OBJ_Door(new Vector(38*gp.TILE_SIZE,37*gp.TILE_SIZE));
    }
}
