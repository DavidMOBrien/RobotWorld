/*
@author David O'Brien (obriedaa)
@version 12-5-2018

Description: Tile Class Used to Construct the Grid            
*/
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;

public class Tile
{
   private int x;
   private int y;
   
   public Tile(int Xcord, int Ycord)
   {
      x = Xcord;
      y = Ycord;
   }
   
   public void paint(Graphics g)
   {
      g.setColor(Color.black);
      g.drawLine(x,y,x+40,y);
      g.drawLine(x,y,x,y+40);
      g.drawLine(x+40,y,x+40,y+40);
      g.drawLine(x,y+40,x+40,y+40);
   }
   
   public int getX()
   {
      return x;
   }
   
   public int getY()
   {
      return y;
   }
}