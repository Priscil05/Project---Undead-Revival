package Tiles;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    static int k;
    public int[][][] mapTileNum;


    public TileManager(GamePanel gp)
    {
        this.gp = gp;

        tile = new Tile[855];
        mapTileNum = new int[3][gp.maxWorldCol][gp.maxWorldRow];
        k = 1;
        getTileImage();
        loadMap();
    }

    void getTileImage()
    {
        tile[0] = new Tile(new BufferedImage(48, 48, BufferedImage.TYPE_INT_ARGB), false, new Rectangle(0,0,0,0));

        GetTileInfo("/Tiles/Buildings.png", 1008, 1440, true, 0, 0, 48, 48);
        GetTileInfo("/Tiles/Road.png", 240, 432, false, 0, 0, 0, 0);
        GetTileInfo("/Tiles/B_roofs.png", 432, 672, true, 0, 24, 48, 24);
        GetTileInfo("/Tiles/OneB_col_obj.png", 288, 336, true,0, 0, 48, 48);
        GetTileInfo("/Tiles/Complx_col_obj.png", 336, 48, true, 18, 0, 12, 23);
        GetTileInfo("/Tiles/Big_colission.png", 96, 48, true,  8, 0, 32, 38);
    }

    void loadMap()
    {
        GetMapInfo("/Map/Road_1.txt", 0);
        GetMapInfo("/Map/Buildings_2.txt", 1);
        GetMapInfo("/Map/B_roofs_3.txt", 2);
    }

    public void draw_layer(Graphics2D g2, int layer)
    {
        int worldcol = 0;
        int worldrow = 0;


        while(worldcol < gp.maxWorldCol && worldrow < gp.maxWorldRow)
        {
            int worldX = worldcol * gp.tileSize;
            int worldY = worldrow * gp.tileSize;
            int screenX = worldX - gp.player.worldPos.x + gp.player.screenX;
            int screenY = worldY - gp.player.worldPos.y + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldPos.x- gp.player.screenX && worldX - gp.tileSize< gp.player.worldPos.x + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldPos.y - gp.player.screenY && worldY - gp.tileSize < gp.player.worldPos.y + gp.player.screenY)
            {
                g2.drawImage(tile[mapTileNum[layer][worldcol][worldrow]].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            }


            worldcol++;

            if(worldcol == gp.maxWorldCol)
            {
                worldcol = 0;
                worldrow++;
            }
        }
    }

    void GetTileInfo(String path, int width, int height, boolean collision, int col_x, int col_y, int col_w, int col_h)
    {
        BufferedImage image;
        try
        {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
            for(int i = 0; i < height; i+=48) {
                for(int j = 0; j < width; j+= 48) {
                    tile[k++] = new Tile(image.getSubimage(j, i, 48, 48), collision, new Rectangle(col_x, col_y, col_w, col_h));
                }
            }
        }catch(IOException e)
        {
        e.printStackTrace();
        }

    }

    void GetMapInfo(String path, int layer) {
        try {
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;


            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(",");
                    int number = Integer.parseInt(numbers[col]);

                    mapTileNum[layer][col][row] = number;
                    col++;
                }
                col = 0;
                row++;

            }
            br.close();
        }
        catch(Exception e)
        {
            System.out.println("Nu sa putut deschide fisierul de leyere");
        }
    }

}




