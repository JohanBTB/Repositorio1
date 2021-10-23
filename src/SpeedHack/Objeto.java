/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpeedHack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author toshiba
 */
public class Objeto {
        int x,y,ancho,alto;
        String tipo;
	Rectangle hitBox;
        double velocidadx=0,velocidady=0;
        boolean gravedad;

	
	public Objeto(int x, int y, int ancho, int alto) {
		this.alto=alto;
		this.ancho=ancho;
		this.x=x;
		this.y=y;
                gravedad=false;
		
		hitBox= new Rectangle(x,y,ancho,alto);
	}
 
        
        public void draw(Graphics2D gtd, int jugadorx, ImageIcon textura, Panel panel) {
		/*gtd.setColor(Color.BLACK);
		gtd.drawRect(jugadorx+x, y, ancho, alto);*/
                if(gravedad){

                velocidady+=0.5;
                if(velocidady>4)velocidady=6;
                this.x+=velocidadx;
                this.y+=velocidady;
                this.hitBox.x=this.x;
                this.hitBox.y=this.y;
                    
                }

                gtd.drawImage((textura.getImage()), jugadorx+x, y, this.ancho, this.alto, panel);
               
	}
        
        public void tirar(int x, int y){
           gravedad=true;
           this.x=x+50;
           this.y=y;
           this.hitBox.y=500;
           this.hitBox.x=x;
                velocidadx=10;
                velocidady=-10;
                


           System.out.println("SE DA");
              
        }
        
        
}
