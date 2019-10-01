/*
@author David O'Brien (obriedaa)
@version 12-5-2018

Description: Teleporter Class used to symbolize where a teleporter is on the map     
*/
import java.awt.*;

public class Teleporter extends Entity
{
   private Image tele;
   
   public Teleporter( int xCord, int yCord )
   {
      super( xCord, yCord );
      super.setID("Teleporter");
      tele = Toolkit.getDefaultToolkit().getImage( "teleporter.png" );
   }
   
   public void paint( Graphics g )
   {
      g.drawImage( tele, super.getX(), super.getY(), null );
   }
   
   public void setTeleporter( int newX, int newY )
   {
      super.setX( newX );
      super.setY( newY );
   }
}