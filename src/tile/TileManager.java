package tile;

import mian.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.MAX_WORLD_COL][gp.MAX_WORLD_ROW];
        getTailImage();
        loadMap("/maps/map1.txt");
    }

    public void getTailImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("world/grass.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("world/wall.png")));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("world/water.png")));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("world/dirt.png")));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("world/treeGrass.png")));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("world/sand.png")));



        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String source){
        try{
            InputStream is = getClass().getResourceAsStream(source);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.MAX_WORLD_COL && row < gp.MAX_WORLD_ROW){
                String line = br.readLine();

                while(col < gp.MAX_WORLD_COL) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.MAX_WORLD_COL){
                    col = 0;
                    row++;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        int worldCol = 0, worldRow = 0;

        while(worldCol < gp.MAX_WORLD_COL && worldRow < gp.MAX_WORLD_ROW){
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.TILE_SIZE;
            int worldY = worldRow * gp.TILE_SIZE;
            int screenX = worldX - gp.player.getPosition().getX() + gp.player.getCameraPosition().getX();
            int screenY = worldY - gp.player.getPosition().getY() + gp.player.getCameraPosition().getY();

            if(worldX + gp.TILE_SIZE > gp.player.getPosition().getX() - gp.player.getCameraPosition().getX() &&
                    (worldX - gp.TILE_SIZE < gp.player.getPosition().getX() + gp.player.getCameraPosition().getX()) &&
                    (worldY + gp.TILE_SIZE > gp.player.getPosition().getY() - gp.player.getCameraPosition().getY()) &&
                    (worldY - gp.TILE_SIZE < gp.player.getPosition().getY() + gp.player.getCameraPosition().getY())) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
            }
            worldCol++;

            if(worldCol == gp.MAX_WORLD_COL){
                worldCol = 0;
                worldRow++;
            }

        }
    }

}
