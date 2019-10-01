/*
@author David O'Brien (obriedaa)
@version 12-5-2018

Description: Robot World controls the game, user's input, the board, and the enemies           
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.image.BufferStrategy;
import java.lang.Math;
import java.util.Vector;
import java.util.Random;
import java.awt.Desktop;
import java.net.URI;

public class RobotWorld extends JFrame
{
   public static final int FRAME_WIDTH = 600;
   public static final int FRAME_HEIGHT = 700;
   
   private BufferStrategy myStrategy;
   private Vector<Tile> tiles;
   private Vector<Entity> entities;
   private Player player;
   private String message;
   private Graphics g;
   private boolean gameOver;
   private boolean playerAlive;
   
   public RobotWorld()
   {
      super();
      setSize ( FRAME_WIDTH, FRAME_HEIGHT );
      setTitle( "Robot World" );
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      tiles = new Vector<Tile>();
      entities = new Vector<Entity>();
      gameOver = false;
      playerAlive = true;
      message = "WELCOME";
      
      JButton newGame = new JButton( "New Game" );
      newGame.addActionListener( new NewGameButtonListener() );
      getContentPane().add( "South", newGame );
      
      addMouseListener( new MouseKeeper() );
      
      this.setVisible(true);
      this.createBufferStrategy(2);
      
      setNewGame();
   }
   
   public void paint( Graphics g )
   {
      while (myStrategy==null)
      {
         myStrategy=this.getBufferStrategy();
         try{
            Thread.sleep(25);
         } 
         catch(Exception e) {}
      }
      g = myStrategy.getDrawGraphics();
      
      super.paint(g);
      
      drawTiles( g );
      drawPlayer( g );
      drawEntities( g );
      drawMessage( g );
      
      myStrategy.show();
      g.dispose();
      Toolkit.getDefaultToolkit().sync();	
   }
   
   public void setNewGame(  )
   {
      gameOver = false;
      message = "NEW GAME STARTED";
      entities.removeAllElements();
      Random rand = new Random();
      playerAlive = true;
      
      int randX = rand.nextInt(10);
      int randY = rand.nextInt(10);
      
      entities.addElement( new Teleporter( 75, 125 ) );
      entities.addElement( new Teleporter( 435, 125 ) );
      entities.addElement( new Teleporter( 75, 485 ) );
      entities.addElement( new Teleporter( 435, 485 ) );
      
      for (int i = 0; i<3; i++)
      {
         randX = 75 + (40 * rand.nextInt(10));
         randY = 125 + (40 * rand.nextInt(10));
        
         ScrapPile newPile = new ScrapPile( randX, randY );
         for (int k = 0; k<entities.size(); k++)
         {
            if ((newPile.getX() == entities.elementAt(k).getX()) && (newPile.getY() == entities.elementAt(k).getY()))
            {
               boolean scrapTaken = true;
              
               while (scrapTaken == true)
               {
                  randX = 75 + (40 * rand.nextInt(10));
                  randY = 125 + (40 * rand.nextInt(10));
                     
                  scrapTaken = checkFree( randX, randY );
               }
               newPile.setScrapPile(randX, randY);
            }
         }
         entities.addElement( newPile );
      }
      
      
      
      for (int i = 0; i<3; i++)
      {
         randX = 75 + (40 * rand.nextInt(10));
         randY = 125 + (40 * rand.nextInt(10));
        
         Enemy newEnemy = new Enemy( randX, randY );
         for (int k = 0; k<entities.size(); k++)
         {
            if ((newEnemy.getX() == entities.elementAt(k).getX()) && (newEnemy.getY() == entities.elementAt(k).getY()))
            {
               boolean enemyTaken = true;
              
               while (enemyTaken == true)
               {
                  randX = 75 + (40 * rand.nextInt(10));
                  randY = 125 + (40 * rand.nextInt(10));
                     
                  enemyTaken = checkFree( randX, randY );
               }
               newEnemy.setEnemy(randX, randY);
            }
         }
         entities.addElement( newEnemy );
      }
      
      randX = 75 + (40 * rand.nextInt(10));
      randY = 125 + (40 * rand.nextInt(10));
   
      player = new Player( randX, randY );
      
      for (int i=0; i<entities.size();i++)
      {
         if ((player.getX() == entities.elementAt(i).getX())
                  && (player.getY() == entities.elementAt(i).getY()))
         {
            boolean taken = true;
            while (taken == true)
            {
               randX = 75 + (40 * rand.nextInt(10));
               randY = 125 + (40 * rand.nextInt(10));
                     
               taken = checkFree( randX, randY );
            }
            player.setPlayer( randX, randY );
         }
      }
      
      repaint();
   }
   
   //---------HELPERS---------
   
   protected void drawTiles( Graphics g )
   {
      int referenceX = 75;
      int referenceY = 125;
      for( int i = 0; i<10; i++)
      {
         referenceX = 75;
         for( int q = 0; q<10; q++)
         {
            Tile newTile = new Tile(referenceX, referenceY);
            newTile.paint( g );
            referenceX += 40;
         }
         referenceY += 40;
      }
   }
   
   protected void drawPlayer( Graphics g )
   {
      player.paint( g );
   }
   
   protected void drawEntities( Graphics g )
   {
      for (int i=0; i<entities.size();i++)
      {
         entities.elementAt(i).paint( g );
      }
   }
   
   protected void drawMessage( Graphics g )
   {
      g.setColor(Color.black);
      g.drawString( message, 250, 100 );
   }
   
   protected void teleportPlayer( Graphics g )
   {
      Random rand = new Random();
      boolean taken = true;
      
      while ( taken == true )
      {
         int newX = 75 + (40 * rand.nextInt(10));
         int newY = 125 + (40 * rand.nextInt(10));
      
         taken = checkFree( newX, newY );
         
         player.setPlayer( newX, newY );
         message = "TELEPORTED";
      } 
   }
   
   protected boolean movePlayer( MouseEvent e )
   {
      if ((e.getX() < (player.getX() + 80 )) && (e.getX() > (player.getX() - 40 ))
               && (e.getY() > (player.getY() - 40)) && (e.getY() < (player.getY() + 80))
                  && (e.getX() > 75) && (e.getX() < 475)
                     && (e.getY() > 125) && (e.getY() < 525))
      {
         if (e.getX() > player.getX())
         {
            player.moveRight();
         }
         if (e.getX() < player.getX())
         {
            player.moveLeft();
         }
         if (e.getY() > player.getY() )
         {
            player.moveDown();
         }
         if (e.getY() < player.getY() )
         {
            player.moveUp();
         }
         message = "VALID MOVE";
      }
      else
      {
         message = "ILLEGAL MOVE";
         return false;
      }
      return true;
   }
   
   protected void playerStatusCheck( )
   {
      for (int i=0; i<entities.size();i++)
      {
         if ((player.getX() == entities.elementAt(i).getX())
                  && (player.getY() == entities.elementAt(i).getY()))
         {
            if (entities.elementAt(i).getID().equals("Teleporter"))
            {
               teleportPlayer( g );
               repaint();
            }
                  
            if (entities.elementAt(i).getID().equals("ScrapPile") || (entities.elementAt(i).getID().equals("Enemy")))
            {
               playerAlive = false;
               gameOver = true;
                     
            }
         }
      }
   }
   
   protected void moveEnemies( )
   {
      for (int i = 0; i<entities.size(); i++)
      {
         if( entities.elementAt(i).getID() == "Enemy" )
         {
            if (player.getX() < entities.elementAt(i).getX())
            {
               entities.elementAt(i).moveLeft();
            }
                  
            if (player.getX() > entities.elementAt(i).getX())
            {
               entities.elementAt(i).moveRight();
            }
                  
            if (player.getY() > entities.elementAt(i).getY())
            {
               entities.elementAt(i).moveDown();
            }
                  
            if (player.getY() < entities.elementAt(i).getY())
            {
               entities.elementAt(i).moveUp();
            }
         }
      }
   }
   
   protected void enemyStatusCheck( )
   {
      int tracker = 0;
      for (int i = 0; i<entities.size(); i++)
      {
         if (entities.elementAt(i).getID().equals("Enemy"))
         {
            for (int k = tracker+1; k<entities.size(); k++)
            {
               if (entities.elementAt(k).getID().equals("Enemy") &&
                        (entities.elementAt(i).getX() == entities.elementAt(k).getX()) &&
                        (entities.elementAt(i).getY() == entities.elementAt(k).getY()))
               {
                  int tempX = entities.elementAt(i).getX();
                  int tempY = entities.elementAt(i).getY();
                        
                  entities.addElement( new ScrapPile( tempX, tempY ));
                  entities.removeElementAt( entities.indexOf(entities.elementAt(i) ) );
                  entities.removeElementAt( entities.indexOf(entities.elementAt(i) ) );
               }
            }
         }
         tracker += 1;
      }
      tracker = 0;
      for (int i = 0; i<entities.size();i++)
      {
         if (entities.elementAt(i).getID().equals("Enemy"))
         {
            for (int k = tracker+1; k<entities.size(); k++)
            {
               if (entities.elementAt(k).getID().equals("ScrapPile") &&
                        (entities.elementAt(i).getX() == entities.elementAt(k).getX()) &&
                        (entities.elementAt(i).getY() == entities.elementAt(k).getY()))
               {
                  entities.removeElementAt( entities.indexOf(entities.elementAt(i) ) );
               }
                     
            }
         }
         tracker += 1;
      }
      tracker = 0;
      for (int i = 0; i<entities.size();i++)
      {
         if (entities.elementAt(i).getID().equals("ScrapPile"))
         {
            for (int k = tracker+1; k<entities.size(); k++)
            {
               if (entities.elementAt(k).getID().equals("Enemy") &&
                        (entities.elementAt(i).getX() == entities.elementAt(k).getX()) &&
                        (entities.elementAt(i).getY() == entities.elementAt(k).getY()))
               {
                  entities.removeElementAt( entities.indexOf(entities.elementAt(k) ) );
               }
            }
         }
      }
   }
   
   protected boolean checkFree( int newX, int newY )
   {
      for (int i = 0; i<entities.size(); i++)
      {
         if ((entities.elementAt(i).getX() == newX) && (entities.elementAt(i).getY() == newY))
         {
            return true;
         }
      }
      return false;
   }
   
   
   
   //--------------INNER CLASSES----------------
   private class NewGameButtonListener implements ActionListener
   {
      public void actionPerformed( ActionEvent e )
      {
         setNewGame();
         repaint();
      }
   }
   
   private class MouseKeeper extends MouseAdapter
   {
      public void mousePressed( MouseEvent e )
      {
         if (gameOver == false)
         {
            boolean continuing = true;
            continuing = movePlayer( e );
            
            repaint();
            
            if (continuing == true)
            {
            
               playerStatusCheck( );
            
               moveEnemies( );
            
               repaint( );
               
               playerStatusCheck( );
            
            //CHECK IF ENEMIES HIT SOMETHING
               enemyStatusCheck( );
            }
               
            if (playerAlive == false)
            {
               message = "YOU LOSE!";
               repaint();
            }
            else
            {
               int check = 0;
               for (int i = 0; i<entities.size();i++)
               {
                  if (entities.elementAt(i).getID().equals("Enemy"))
                  {
                     check += 1;
                  }
               }
               if (check == 0)
               {
                  message = "YOU WIN!";
                  repaint();
                  gameOver = true;
               }
            }
               
         }
      }
   }
   
   public static void main(String[] args)
   {
      Desktop d = Desktop.getDesktop();
      String stElmosFire = "https://www.youtube.com/watch?v=jVf4_WglzWA";
      
      try
      {
         d.browse(new URI(stElmosFire));
      }
      catch(Exception e) {}
   
      RobotWorld world = new RobotWorld();
      world.setVisible(true);
   }
}