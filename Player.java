/*
@author David O'Brien (obriedaa)
@version 12-5-2018

Description: Player Class Controlled by User          
*/
import java.awt.*;

public class Player
{
   int x;
   int y;
   private Image playerIcon;
   
   public Player(int xCord, int yCord)
   {
      x = xCord;
      y = yCord;
      playerIcon = Toolkit.getDefaultToolkit().getImage( "player.png" );
   }
   
   public int getX()
   {
      return x;
   }
   
   public int getY()
   {
      return y;
   }
   
   public void setPlayer( int newX, int newY )
   {
      x = newX;
      y = newY;
   }
   
   public void paint( Graphics g )
   {
      g.drawImage( playerIcon, x, y, null );
   }
   
   public void moveLeft()
   {
      x -= 40;
   }
   
   public void moveRight()
   {
      x += 40;
   }
   
   public void moveUp()
   {
      y -= 40;
   }
   
   public void moveDown()
   {
      y += 40;
   }
}