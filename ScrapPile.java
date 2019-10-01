/*
@author David O'Brien (obriedaa)
@version 12-5-2018

Description: Scrap Pile Class used to represent where a Scrap Pile is on the map        
*/
import java.awt.*;

public class ScrapPile extends Entity
{
   private Image scrap;
   
   public ScrapPile( int xCord, int yCord )
   {
      super(xCord, yCord);
      super.setID("ScrapPile");
      scrap = Toolkit.getDefaultToolkit().getImage( "scrapPile.png" );
   }
   
   public void paint( Graphics g )
   {
      g.drawImage( scrap, super.getX(), super.getY(), null );
   }
   
   public void setScrapPile( int newX, int newY )
   {
      super.setX( newX );
      super.setY( newY );
   }
   
}