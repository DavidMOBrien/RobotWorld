/*
@author David O'Brien (obriedaa)
@version 12-5-2018

Description: Enemy class used to represent an enemy on the map and is able to move it    
*/
import java.awt.*;

public class Enemy extends Entity
{
   private Rectangle location;
   private Image evilbot;
   
   public Enemy( int xCord, int yCord )
   {
      super(xCord, yCord);
      super.setID("Enemy");
      evilbot = Toolkit.getDefaultToolkit().getImage( "evilbot.png" );
   }
   
   public void paint( Graphics g )
   {
      g.drawImage( evilbot, super.getX(), super.getY(), null );
   }
   
   public void setEnemy( int newX, int newY )
   {
      super.setX( newX );
      super.setY( newY );
   }
   
   public void moveLeft()
   {
      super.setX( super.getX()-40);
   }
   
   public void moveRight()
   {
      super.setX( super.getX()+40);
   }
   
   public void moveUp()
   {
      super.setY( super.getY()-40);
   }
   
   public void moveDown()
   {
      super.setY( super.getY()+40);
   }

}