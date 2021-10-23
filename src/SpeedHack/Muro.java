package SpeedHack;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Muro {
	int x,y,ancho,alto;
	int spritex,spritey;
	Rectangle hitBox;
	
	public Muro(int x, int y, int ancho, int alto, int spritex,int spritey) {
		this.alto=alto;
		this.ancho=ancho;
		this.x=x;
		this.y=y;
                this.spritex=spritex;
                this.spritey=spritey;
		hitBox= new Rectangle(x,y,ancho,alto);
		
	}
 	
        public void draw(Graphics2D gtd, int posicionx, ImageIcon textura ,Panel panel) {;
                
                gtd.drawImage(textura.getImage(), posicionx+x, y, posicionx+x+ancho, y+alto, spritex*40+40, spritey*40, spritex*40, spritey*40+40, panel);
                
}
}
