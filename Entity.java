package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {


    public Point worldPos;



    public int speed;

    GamePanel gp;

    public BufferedImage up1, up2;
    public BufferedImage down1, down2;
    public BufferedImage left1, left2, left3, left4;
    public BufferedImage right1, right2, right3, right4;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public boolean collisionOn = false;

    public int calc_tileIndex(Point pos, int layer)
    {
        int tile_posX = pos.x/gp.tileSize;
        int tile_posY = pos.y/gp.tileSize;

        int tileIndex;
        tileIndex = gp.tileM.mapTileNum[layer][tile_posX][tile_posY];

        return tileIndex;
    }
}

