package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import Tiles.Tile;

public class Player extends Entity{

    public final int screenX;
    public final int screenY;
    KeyHandler keyH;
    Tile current_tile;


    public Player(GamePanel gp, KeyHandler keyH)
    {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;



        solidArea = new Rectangle(16, 34, 15, 14);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues()
    {
        worldPos = new Point(gp.tileSize*20, gp.tileSize*25);
        speed = 5;

        solidArea.x += screenX;
        solidArea.y += screenY;

        direction = "left";
    }

    public void getPlayerImage()
    {
        try
        {
             up1 = ImageIO.read(getClass().getResourceAsStream("/Player/sw_up_1.png"));
             up2 = ImageIO.read(getClass().getResourceAsStream("/Player/sw_up_2.png"));
             down1 = ImageIO.read(getClass().getResourceAsStream("/Player/sw_down_1.png"));
             down2 = ImageIO.read(getClass().getResourceAsStream("/Player/sw_down_2.png"));
             left1 = ImageIO.read(getClass().getResourceAsStream("/Player/sw_left_1.png"));
             left2 = ImageIO.read(getClass().getResourceAsStream("/Player/sw_left_2.png"));
             left3 = ImageIO.read(getClass().getResourceAsStream("/Player/sw_left_3.png"));
             left4 = ImageIO.read(getClass().getResourceAsStream("/Player/sw_left_4.png"));
             right1 = ImageIO.read(getClass().getResourceAsStream("/Player/sw_right_1.png"));
             right2 = ImageIO.read(getClass().getResourceAsStream("/Player/sw_right_2.png"));
             right3 = ImageIO.read(getClass().getResourceAsStream("/Player/sw_right_3.png"));
             right4 = ImageIO.read(getClass().getResourceAsStream("/Player/sw_right_4.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void update()
    {

        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed)
        {
            if(keyH.upPressed)
            {
                direction = "up";
            }
            else if(keyH.downPressed)
            {
                direction = "down";
            }
            else if(keyH.leftPressed)
            {
                direction = "left";
            }
            else if(keyH.rightPressed)
            {
                direction = "right";
            }


            //check tile collision

            int layer = 2;

            collisionOn = gp.Colchk.checkCollision(this.direction, this.speed, this.worldPos, this.solidArea,
                    gp.tileM.tile[this.calc_tileIndex(this.worldPos, layer)].GetColArea(), true
                    /*gp.tileM.tile[this.calc_tileIndex(this.worldPos, layer)].GetCollision()*/);

            if(collisionOn == false) {

                switch (direction) {
                    case "up":
                        worldPos.y -= speed;
                        break;
                    case "down":
                        worldPos.y += speed;
                        break;
                    case "left":
                        worldPos.x -= speed;
                        break;
                    case "right":
                        worldPos.x += speed;
                        break;
                }

                spriteCounter++;
                if (spriteCounter >= 4) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 3;
                    } else if (spriteNum == 3) {
                        spriteNum = 4;
                    } else if (spriteNum == 4) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }

       }

    }

    public void draw(Graphics2D g2) // aici poti sa vii vu imaginea de trebuie randata in functie de ce tii in mana si asa nu tre sa faci pt fiecare arma cate un carnat de asta
    {
        BufferedImage image = null;

        switch(direction)
        {
            case "up":
                if(spriteNum == 1)
                {
                    image = up1;
                }
                else if(spriteNum == 2)
                {
                    image = up2;
                }
                else if(spriteNum == 3)
                {
                    image = up2;
                }
                else if(spriteNum == 4)
                {
                    image = up1;
                }
                break;
            case "down":
                if (spriteNum == 1)
                {
                    image = down1;
                }
                else if(spriteNum == 2)
                {
                    image = down2;
                }
                else if(spriteNum == 3)
                {
                    image = down2;
                }
                else if(spriteNum == 4)
                {
                    image = down1;
                }
                break;
            case "left":
                if(spriteNum == 1)
                {
                    image = left1;
                }
                else if(spriteNum == 2)
                {
                    image = left2;
                }
                else if(spriteNum == 3)
                {
                    image = left3;
                }
                else if(spriteNum == 4)
                {
                    image = left4;
                }
                break;
            case "right":
                if(spriteNum == 1)
                {
                    image = right1;
                }
                else if(spriteNum == 2)
                {
                    image = right2;
                }
                else if(spriteNum == 3)
                {
                    image = right3;
                }
                else if(spriteNum == 4)
                {
                    image = right4;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.playerTileSize, gp.playerTileSize, null);
        g2.setColor(Color.RED);
        g2.drawRect(solidArea.x, solidArea.y, solidArea.width, solidArea.height);
    }
}
