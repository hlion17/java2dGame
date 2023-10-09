package org.example;

import org.example.entity.Entity;
import org.example.object.SuperObject;

import java.util.Objects;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.TILE_SIZE;
        int entityRightCol = entityRightWorldX / gp.TILE_SIZE;
        int entityTopRow = entityTopWorldY / gp.TILE_SIZE;
        int entityBottomRow = entityBottomWorldY / gp.TILE_SIZE;

        int tileNum1, tileNum2;

        if (entity.direction == Entity.Direction.UP) {
            entityTopRow = (entityTopWorldY - entity.speed) / gp.TILE_SIZE;
            tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
            if (gp.tileManager.tiles[tileNum1].collision
                    || gp.tileManager.tiles[tileNum2].collision) {
                entity.collisionOn = true;
            }
        } else if (entity.direction == Entity.Direction.DOWN) {
            entityBottomRow = (entityBottomWorldY + entity.speed) / gp.TILE_SIZE;
            tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
            tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
            if (gp.tileManager.tiles[tileNum1].collision
                    || gp.tileManager.tiles[tileNum2].collision) {
                entity.collisionOn = true;
            }
        } else if (entity.direction == Entity.Direction.LEFT) {
            entityLeftCol = (entityLeftWorldX - entity.speed) / gp.TILE_SIZE;
            tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
            if (gp.tileManager.tiles[tileNum1].collision
                    || gp.tileManager.tiles[tileNum2].collision) {
                entity.collisionOn = true;
            }
        } else if (entity.direction == Entity.Direction.RIGHT) {
            entityRightCol = (entityRightWorldX + entity.speed) / gp.TILE_SIZE;
            tileNum1 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
            tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
            if (gp.tileManager.tiles[tileNum1].collision
                    || gp.tileManager.tiles[tileNum2].collision) {
                entity.collisionOn = true;
            }
        }
    }

    public int checkObject(Entity entity, boolean isPlayer) {
        int index = 999;

        for (int i = 0; i < gp.obj.length; i++) {
            SuperObject o = gp.obj[i];
            if (o != null) {
                // Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                // Get the object's solid area position
                o.solidArea.x = o.worldX + o.solidArea.x;
                o.solidArea.y = o.worldY + o.solidArea.y;

                switch (entity.direction) {
                    case UP:
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(o.solidArea)) {
                            if (o.collision) entity.collisionOn = true;
                            if (isPlayer) index = i;
                        }
                        break;
                    case DOWN:
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(o.solidArea)) {
                            if (o.collision) entity.collisionOn = true;
                            if (isPlayer) index = i;
                        }
                        break;
                    case LEFT:
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(o.solidArea)) {
                            if (o.collision) entity.collisionOn = true;
                            if (isPlayer) index = i;
                        }
                        break;
                    case RIGHT:
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(o.solidArea)) {
                            if (o.collision) entity.collisionOn = true;
                            if (isPlayer) index = i;
                        }
                        break;
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                o.solidArea.x = o.solidAreaDefaultX;
                o.solidArea.y = o.solidAreaDefaultY;
            }
        }

        return index;
    }
}
