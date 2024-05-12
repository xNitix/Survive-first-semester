package mian;

import entity.Entity;
import object.AObject;

public class CollisonChecker {

    GamePanel gp;
    public CollisonChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        int leftHitBox = entity.position.getX() + entity.trueHitBox.x;
        int rightHitBox = entity.position.getX() + entity.trueHitBox.x + entity.trueHitBox.width;
        int topHitBox = entity.position.getY() + entity.trueHitBox.y;
        int bottomHitBox = entity.position.getY() + entity.trueHitBox.y + entity.trueHitBox.height;

        int leftCol = leftHitBox/gp.TILE_SIZE;
        int rightCol = rightHitBox/gp.TILE_SIZE;
        int topRow = topHitBox/gp.TILE_SIZE;
        int bottomRow = bottomHitBox/gp.TILE_SIZE;

        int tileNum1, tileNum2;

        switch(entity.direction){
            case"upR"->{
                int topRowTemp = topRow;
                topRow = (topHitBox - entity.speed)/gp.TILE_SIZE;
                tileNum1 = gp.tileM.mapTileNum[leftCol][topRow];
                tileNum2 = gp.tileM.mapTileNum[rightCol][topRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionUD = true;
                }
                rightCol = (rightHitBox + entity.speed)/gp.TILE_SIZE;
                tileNum1 = gp.tileM.mapTileNum[rightCol][topRowTemp];
                tileNum2 = gp.tileM.mapTileNum[rightCol][bottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionLR = true;
                }
            }

            case"upL"->{
                int topRowTemp = topRow;
                topRow = (topHitBox - entity.speed)/gp.TILE_SIZE;
                tileNum1 = gp.tileM.mapTileNum[leftCol][topRow];
                tileNum2 = gp.tileM.mapTileNum[rightCol][topRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionUD = true;
                }
                leftCol = (leftHitBox - entity.speed)/gp.TILE_SIZE;
                tileNum1 = gp.tileM.mapTileNum[leftCol][topRowTemp];
                tileNum2 = gp.tileM.mapTileNum[leftCol][bottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionLR = true;
                }
            }

            case "up"->{
                topRow = (topHitBox - entity.speed)/gp.TILE_SIZE;
                tileNum1 = gp.tileM.mapTileNum[leftCol][topRow];
                tileNum2 = gp.tileM.mapTileNum[rightCol][topRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionUD = true;
                }
            }
            case "down"->{
                bottomRow = (bottomHitBox + entity.speed)/gp.TILE_SIZE;
                tileNum1 = gp.tileM.mapTileNum[leftCol][bottomRow];
                tileNum2 = gp.tileM.mapTileNum[rightCol][bottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionUD = true;
                }
            }
            case "downR"->{
                int bottomRow2 = bottomRow;
                bottomRow = (bottomHitBox + entity.speed)/gp.TILE_SIZE;
                tileNum1 = gp.tileM.mapTileNum[leftCol][bottomRow];
                tileNum2 = gp.tileM.mapTileNum[rightCol][bottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionUD = true;
                }

                rightCol = (rightHitBox + entity.speed)/gp.TILE_SIZE;
                tileNum1 = gp.tileM.mapTileNum[rightCol][topRow];
                tileNum2 = gp.tileM.mapTileNum[rightCol][bottomRow2];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionLR = true;
                }
            }

            case "downL"->{
                int bottomRow2 = bottomRow;
                bottomRow = (bottomHitBox + entity.speed)/gp.TILE_SIZE;
                tileNum1 = gp.tileM.mapTileNum[leftCol][bottomRow];
                tileNum2 = gp.tileM.mapTileNum[rightCol][bottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionUD = true;
                }

                leftCol = (leftHitBox - entity.speed)/gp.TILE_SIZE;
                tileNum1 = gp.tileM.mapTileNum[leftCol][topRow];
                tileNum2 = gp.tileM.mapTileNum[leftCol][bottomRow2];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionLR = true;
                }
            }

            case "right"->{
                rightCol = (rightHitBox + entity.speed)/gp.TILE_SIZE;
                tileNum1 = gp.tileM.mapTileNum[rightCol][topRow];
                tileNum2 = gp.tileM.mapTileNum[rightCol][bottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionLR = true;
                }
            }
            case "left"->{
                leftCol = (leftHitBox - entity.speed)/gp.TILE_SIZE;
                tileNum1 = gp.tileM.mapTileNum[leftCol][topRow];
                tileNum2 = gp.tileM.mapTileNum[leftCol][bottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionLR = true;
                }
            }
        }
    }

    public int checkObject(Entity entity, boolean player){
        int index = 999;

        for(int i = 0; i < gp.getObjects().length; i++) {
            AObject[] objects = gp.getObjects();
            if(objects[i] != null){
                // get entity's solid area position
                entity.trueHitBox.x = entity.position.getX() + entity.trueHitBox.x;
                entity.trueHitBox.y = entity.position.getY() + entity.trueHitBox.y;

                // get the object's solid area position
                objects[i].getSolidArea().x = objects[i].getPosition().getX() + objects[i].getSolidArea().x;
                objects[i].getSolidArea().y = objects[i].getPosition().getY() + objects[i].getSolidArea().y;

                switch (entity.direction){
                    case "up" -> {
                        entity.trueHitBox.y -= entity.speed;
                        if (entity.trueHitBox.intersects(objects[i].getSolidArea())) {
                            if(objects[i].isCollision()){
                                entity.collisionUD = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                    }
                    case "upL" -> {
                        entity.trueHitBox.y -= entity.speed;
                        entity.trueHitBox.x -= entity.speed;
                        if (entity.trueHitBox.intersects(objects[i].getSolidArea())) {
                            if(objects[i].isCollision()){
                                entity.collisionUD = true;
                                entity.collisionLR = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                    }
                    case "upR" -> {
                        entity.trueHitBox.y -= entity.speed;
                        entity.trueHitBox.x += entity.speed;
                        if (entity.trueHitBox.intersects(objects[i].getSolidArea())) {
                            if(objects[i].isCollision()){
                                entity.collisionUD = true;
                                entity.collisionLR = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                    }

                    case "down" -> {
                        entity.trueHitBox.y += entity.speed;
                        if (entity.trueHitBox.intersects(objects[i].getSolidArea())) {
                            if(objects[i].isCollision()){
                                entity.collisionUD = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                    }
                    case "downL" -> {
                        entity.trueHitBox.y += entity.speed;
                        entity.trueHitBox.x -= entity.speed;
                        if (entity.trueHitBox.intersects(objects[i].getSolidArea())) {
                            if(objects[i].isCollision()){
                                entity.collisionUD = true;
                                entity.collisionLR = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                    }
                    case "downR" -> {
                        entity.trueHitBox.y += entity.speed;
                        entity.trueHitBox.x += entity.speed;
                        if (entity.trueHitBox.intersects(objects[i].getSolidArea())) {
                            if(objects[i].isCollision()){
                                entity.collisionUD = true;
                                entity.collisionLR = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                    }
                    case "left" -> {
                        entity.trueHitBox.y -= entity.speed;
                        if (entity.trueHitBox.intersects(objects[i].getSolidArea())) {
                            if(objects[i].isCollision()){
                                entity.collisionLR = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                    }
                    case "right" -> {
                        entity.trueHitBox.y += entity.speed;
                        if (entity.trueHitBox.intersects(objects[i].getSolidArea())) {
                            if(objects[i].isCollision()){
                                entity.collisionLR = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                    }
                }
                entity.trueHitBox.x = entity.getSolidAreaDefaultX();
                entity.trueHitBox.y = entity.getSolidAreaDefaultY();
                objects[i].getSolidArea().x = objects[i].getSolidAreaDefaultX();
                objects[i].getSolidArea().y = objects[i].getSolidAreaDefaultY();
            }
        }
        return index;
    }

}
