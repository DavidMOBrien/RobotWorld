/*
@author David O'Brien (obriedaa)
@version 12-5-2018

Description: Entity Class other classes of obstacles extend         
*/
import java.awt.*;

public class Entity
{
   private int x;
   private int y;
   private String ID;
   
   public Entity( int xCord, int yCord )
   {
      x = xCord;
      y = yCord;
      ID = "Entity";
   }
   
   public int getX()
   {
      return x;
   }
   
   public int getY()
   {
      return y;
   }
   
   public void setX( int newX )
   {
      x = newX;
   }
   
   public void setY( int newY )
   {
      y = newY;
   }
   
   public void paint(Graphics g)
   {
   }
   
   public String getID()
   {
      return ID;
   }
   
   public void setID(String aString)
   {
      ID = aString;
   }
   
   public void moveLeft()
   {
   }
   
   public void moveRight()
   {
   }
   
   public void moveUp()
   {
   }
   
   public void moveDown()
   {
   }
}