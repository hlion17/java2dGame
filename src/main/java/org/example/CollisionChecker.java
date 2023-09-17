package org.example;

import org.example.entity.Entity;

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
            entityBottomRow = (entityTopWorldY + entity.speed) / gp.TILE_SIZE;
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
}
