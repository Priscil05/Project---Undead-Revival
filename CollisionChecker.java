package main;

import entity.Entity;
import java.awt.*;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public boolean checkCollision(String direction, int speed, Point entityPos, Rectangle playerSolidArea, Rectangle tileSolidArea, boolean collision) {

        int tileX;
        int tileY;


        boolean collisionDetected = false;

        if (collision) {
            //System.out.println("Are coliziune\n");
            switch (direction) {
                case "up":
                    tileX = entityPos.x / 48;
                    tileY = (entityPos.y)  / 48;
                    tileSolidArea.x += tileX*48;
                    tileSolidArea.y += tileY*48;
                    collisionDetected = playerSolidArea.intersects(tileSolidArea);
                    System.out.println("sus\n");
                    break;
                case "down":
                    tileX = entityPos.x / 48;
                    tileY = (entityPos.y) / 48;
                    tileSolidArea.x += tileX*48;
                    tileSolidArea.y += tileY*48;
                    collisionDetected = playerSolidArea.intersects(tileSolidArea);
                    System.out.println("jos\n");
                    break;
                case "left":
                    tileX = (entityPos.x) / 48;
                    tileY = entityPos.y / 48;
                    tileSolidArea.x += tileX*48;
                    tileSolidArea.y += tileY*48;
                    collisionDetected = playerSolidArea.intersects(tileSolidArea);
                    System.out.println("stanga\n");
                    break;
                case "right":
                    tileX = (entityPos.x) / 48;
                    tileY = entityPos.y / 48;
                    tileSolidArea.x += tileX*48;
                    tileSolidArea.y += tileY*48;
                    collisionDetected = playerSolidArea.intersects(tileSolidArea);
                    System.out.println("dreapta\n");
                    break;
            }
        }
        if(collisionDetected) {
            System.out.println("Are coliziune\n");
        }
        return collisionDetected;
    }
}

/*

        int entityLeftX = entityPos.x + playerSolidArea.x;
        int entityRightX = entityPos.x + playerSolidArea.x + playerSolidArea.width;
        int entityTopY = entityPos.y + playerSolidArea.y;
        int entityBottomY = entityPos.y + playerSolidArea.y + playerSolidArea.height;

        entityLeftX

        int tileLeftX = entityPos.x/gp.tileSize;
        int tileRightX = entityPos.x/gp.tileSize;
        int tileTopY = entityPos.y/gp.tileSize;
        int tileBottomY= entityPos.y/gp.tileSize;

        if(collision)
        {
            switch(direction)
            {
                case "UP":

                    break;
                case "DOWN":
                    break;
                case "LEFT":
                    break;
                case "RIGHT":
                    break;
            }
        }
}

    public static boolean checkCollision(String playerDirection, int playerSpeed, Point playerPosition, Rectangle playerSolidArea, Rectangle tileSolidArea, boolean tileHasCollision) {
        // Calculate the player's next position based on their direction and speed
        Point nextPlayerPosition = getNextPosition(playerDirection, playerSpeed, playerPosition);

        // Create a rectangle representing the player's next solid area
        Rectangle nextPlayerSolidArea = new Rectangle(nextPlayerPosition.x, nextPlayerPosition.y, playerSolidArea.width, playerSolidArea.height);

        // Check if the player's next solid area intersects with the tile's solid area
        if (nextPlayerSolidArea.intersects(tileSolidArea)) {
            // If the tile has collision, return true to indicate a collision occurred
            if (tileHasCollision) {
                return true;
            } else {
                // If the tile does not have collision, adjust the player's position to avoid the tile
                switch (playerDirection) {
                    case "UP":
                        playerPosition.y = tileSolidArea.y + tileSolidArea.height + playerSolidArea.height / 2;
                        break;
                    case "DOWN":
                        playerPosition.y = tileSolidArea.y - playerSolidArea.height / 2;
                        break;
                    case "LEFT":
                        playerPosition.x = tileSolidArea.x + tileSolidArea.width + playerSolidArea.width / 2;
                        break;
                    case "RIGHT":
                        playerPosition.x = tileSolidArea.x - playerSolidArea.width / 2;
                        break;
                }
                return false;
            }
        } else {
            // If there is no collision, update the player's position and return false
            //playerPosition.setLocation(nextPlayerPosition);
            return false;
        }
    }

    public static Point getNextPosition(String direction, int speed, Point currentPosition) {
        switch (direction) {
            case "UP":
                return new Point(currentPosition.x, currentPosition.y - speed);
            case "DOWN":
                return new Point(currentPosition.x, currentPosition.y + speed);
            case "LEFT":
                return new Point(currentPosition.x - speed, currentPosition.y);
            case "RIGHT":
                return new Point(currentPosition.x + speed, currentPosition.y);
            default:
                return currentPosition;
        }
    }


    public void checkTile(Entity entity)
    {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNumber;

        switch(entity.direction)
        {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNumber = gp.tileM.mapTileNum[1][entityLeftCol][entityTopRow];

                if(gp.tileM.tile[tileNumber].GetCollision())
                {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNumber = gp.tileM.mapTileNum[1][entityLeftCol][entityBottomRow];

                if(gp.tileM.tile[tileNumber].GetCollision())
                {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNumber = gp.tileM.mapTileNum[1][entityLeftCol][entityTopRow];

                if(gp.tileM.tile[tileNumber].GetCollision())
                {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNumber = gp.tileM.mapTileNum[1][entityRightCol][entityTopRow];

                if(gp.tileM.tile[tileNumber].GetCollision())
                {
                    entity.collisionOn = true;
                }
                break;
        }

    }*/
