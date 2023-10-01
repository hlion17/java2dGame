package org.example;

import org.example.object.OBJ_Chest;
import org.example.object.OBJ_Drawer;
import org.example.object.OBJ_key;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_key();
        gp.obj[0].worldX = 1150;
        gp.obj[0].worldY = 950;

        gp.obj[1] = new OBJ_key();
        gp.obj[1].worldX = 1252;
        gp.obj[1].worldY = 908;

        gp.obj[2] = new OBJ_Drawer();
        gp.obj[2].worldX = 220;
        gp.obj[2].worldY = 160;

        gp.obj[3] = new OBJ_Chest();
        gp.obj[3].worldX = 412;
        gp.obj[3].worldY = 160;
    }
}
