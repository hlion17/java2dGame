package org.example;

import org.example.object.OBJ_Chest;
import org.example.object.OBJ_Drawer;
import org.example.object.OBJ_Boots;
import org.example.object.OBJ_key;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_key(gp);
        gp.obj[0].worldX = 24 * gp.TILE_SIZE;
        gp.obj[0].worldY = 19 * gp.TILE_SIZE;

        gp.obj[1] = new OBJ_key(gp);
        gp.obj[1].worldX = 25 * gp.TILE_SIZE;
        gp.obj[1].worldY = 20 * gp.TILE_SIZE;

        gp.obj[2] = new OBJ_Drawer(gp);
        gp.obj[2].worldX = 9 * gp.TILE_SIZE;
        gp.obj[2].worldY = 10 * gp.TILE_SIZE;

        gp.obj[3] = new OBJ_Chest(gp);
        gp.obj[3].worldX = 9 * gp.TILE_SIZE;
        gp.obj[3].worldY = 7 * gp.TILE_SIZE;

        gp.obj[4] = new OBJ_Chest(gp);
        gp.obj[4].worldX = 10 * gp.TILE_SIZE;
        gp.obj[4].worldY = 7 * gp.TILE_SIZE;

        gp.obj[5] = new OBJ_Chest(gp);
        gp.obj[5].worldX = 11 * gp.TILE_SIZE;
        gp.obj[5].worldY = 7 * gp.TILE_SIZE;

        gp.obj[6] = new OBJ_Boots(gp);
        gp.obj[6].worldX = 23 * gp.TILE_SIZE;
        gp.obj[6].worldY = 7 * gp.TILE_SIZE;
    }
}
