package main;

import javax.swing.*;
import java.awt.*;

import Tiles.Tile;
import Tiles.TileManager;
import entity.Player;

public class GamePanel extends JPanel implements Runnable {
    //screen settings

    public final int playerTileSize = 48;
    public final int tileSize = 48;
    public final int maxScreenCol = 34; //40
    public final int maxScreenRow = 17; //22
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //world map parameters

    public final int maxWorldCol = 135;
    public final int maxWorldRow = 85;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    public final int layer = 0;

    //FPS
    int FPS = 60;

    public TileManager tileM = new TileManager(this);

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    public CollisionChecker Colchk = new CollisionChecker(this);

   public Player player = new Player(this, keyH);

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() // uses run() method
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; // 0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;
        double timer = 0;
        double drawCount = 0;

        while(gameThread != null){



            update();
            repaint();
            drawCount++;

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                timer += remainingTime;
                remainingTime = remainingTime /1000000;
                if(remainingTime < 0)
                {
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime);

                nextDrawTime +=drawInterval;
                if(timer >= 1000000000)
                {
                    //System.out.println("FPS: " + drawCount);
                    drawCount = 0;
                    timer = 0;
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update()
    {
        player.update();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tileM.draw_layer(g2, 0);
        tileM.draw_layer(g2, 1);

        player.draw(g2);

        tileM.draw_layer(g2, 2);

        g2.dispose();
    }
}


