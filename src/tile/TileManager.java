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
        mapTileNum = new int[gp.MAX_SCREEN_COLUM][gp.MAX_SCREEN_ROW];
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

            while(col < gp.MAX_SCREEN_COLUM && row < gp.MAX_SCREEN_ROW){
                String line = br.readLine();

                while(col < gp.MAX_SCREEN_COLUM) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.MAX_SCREEN_COLUM){
                    col = 0;
                    row++;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        int col = 0, row = 0, x = 0, y = 0;

        while(col < gp.MAX_SCREEN_COLUM && row < gp.MAX_SCREEN_ROW){
            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
            col++;
            x += gp.TILE_SIZE;

            if(col == gp.MAX_SCREEN_COLUM){
                col = 0;
                x = 0;
                row++;
                y += gp.TILE_SIZE;
            }

        }
    }

}
