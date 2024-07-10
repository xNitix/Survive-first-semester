package entity;

import mian.GamePanel;
import mian.KeyHandler;
import object.AObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;
    public final Vector cameraPosition;

    private int keyNumber = 0;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        cameraPosition = new Vector(gp.SCREEN_WIDTH/2 - (gp.TILE_SIZE/2), gp.SCREEN_HEIGHT/2- (gp.TILE_SIZE/2));

        trueHitBox = new Rectangle(8,16,32,32);

        solidAreaDefaultX = 8;
        solidAreaDefaultY = 16;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        position = new Vector(24*gp.TILE_SIZE,24*gp.TILE_SIZE);
        speed = 5;
        direction = "down";
    }

    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/7.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/8.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/3.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/5.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/4.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/6.png")));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyH.upPressed || keyH.downPressed || keyH.rightPressed || keyH.leftPressed){
            int xDisplacement = 0;
            int yDisplacement = 0;

            if (keyH.upPressed) {
                if (keyH.rightPressed) {
                    direction = "upR";
                } else if (keyH.leftPressed) {
                    direction = "upL";
                } else {
                    direction = "up";
                }
            } else if (keyH.downPressed) {
                if (keyH.rightPressed) {
                    direction = "downR";
                } else if (keyH.leftPressed) {
                    direction = "downL";
                } else {
                    direction = "down";
                }
            } else if (keyH.rightPressed) {
                direction = "right";
            } else {
                direction = "left";
            }

            collisionUD = false;
            collisionLR = false;

            // check tile collision
            gp.cChecker.checkTile(this);

            // check object collision
            int objIndex = gp.cChecker.checkObject(this,true);
            pickUpObject(objIndex);

            if(!collisionUD){
                switch (direction){
                    case "upL", "up", "upR" -> {
                        yDisplacement -= speed;
                    }case "down", "downR", "downL" -> {
                        yDisplacement += speed;
                    }case "right" -> {
                    }case "left" ->{}
                }
            }

            if(!collisionLR){
                switch (direction){
                    case "upR", "downR", "right" -> {
                        xDisplacement += speed;
                    }case "upL", "downL", "left" -> {
                        xDisplacement -= speed;
                    }case "up" -> {
                    }case "down" -> {
                    }
                }
            }
            if(!collisionUD || !collisionLR) position.vectorAdd(xDisplacement, yDisplacement, speed);

            spriteCounter++;
            if(spriteCounter > 10){
                if(spriteNum == 1){
                    spriteNum = 2;
                } else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    private void pickUpObject(int index) {
        if(index != 999) {
            AObject[] objects = gp.getObjects();
            AObject object = objects[index];
            if(object.interaction(this)) {
                objects[index] = null;
            }
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "right", "upR", "downR":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
            case "left", "upL", "downL":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
        }
        g2.drawImage(image, cameraPosition.getX(), cameraPosition.getY(), gp.TILE_SIZE, gp.TILE_SIZE, null);
    }

    public Vector getPosition() {
        return position;
    }

    public Vector getCameraPosition() {
        return cameraPosition;
    }

    public void increaseKeyNumber() {
        this.keyNumber ++;
    }

    public void decreaseKeyNumber() {
        this.keyNumber --;
    }

    public int getKeyNumber() {
        return keyNumber;
    }
}
