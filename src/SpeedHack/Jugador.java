/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpeedHack;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

/*Hola que hace*/
/**
 *
 * @author toshiba
 */
public class Jugador{
        Panel panel;
        //JUGADOR-CARACTERISTICAS
	int x,y,ancho,alto;
        Rectangle hitBox;
        Objeto item;
	String direccion="Derecha";
        String saltar="";
        Boolean gameOver=false;
        int vidas;
        
        // HABILIDADES
	ArrayList <Objeto> lista= new ArrayList(2);
	double velocidadx,velocidady;
	boolean izquierda,derecha,arriba,abajo,turbo,tirar;
        float recarga_turbo;
        
        
        //SKIN
        int Incremento_total=1;
        int Incremento_aumento=0;
        int Incremento_normal=0;
        int Incremento_turbo=7;

        public String skin;
	
	public Jugador(int x, int y,int vidas, Panel panel) {
		this.panel=panel;
		this.x=x;
		this.y=y;
		velocidadx=0;
		velocidady=0;
		ancho=40;/*cmabios*/
		alto=60;
                item=null;
		hitBox=new Rectangle(x,y,ancho,alto);/*AJUSTE DE HITBOX*/
                recarga_turbo=0;
                this.vidas=vidas;
                
                //temporal-prueba
                skin="Frankie";
	}

	
	public void pasivo() {
		
		velocidady+=0.3;
		if(velocidadx>0 && velocidadx<0.75) velocidadx=0;
		if(velocidadx<0 && velocidadx>-0.75) velocidadx=0;
			//COLISION HORIZONTAL
		hitBox.x+=velocidadx;
		for(Muro muro: panel.suelo){
			  if(hitBox.intersects(muro.hitBox)) { 
				  hitBox.x-=velocidadx;
				  while(!muro.hitBox.intersects(hitBox)) hitBox.x+= Math.signum(velocidadx);
				  hitBox.x-= Math.signum(velocidadx);
				  velocidadx=0;
				  x=hitBox.x;
			  
			  }
			}
			
		
			//COLISION VERTICAL
		hitBox.y+=velocidady;
               for(Muro muro: panel.suelo){
			  if(hitBox.intersects(muro.hitBox)) {
                              
                                    
                              
				  hitBox.y-=velocidady;
				  while(!muro.hitBox.intersects(hitBox)) hitBox.y+= Math.signum(velocidady);
				  hitBox.y-= Math.signum(velocidady);
				  velocidady=0;
				  y=hitBox.y;
			  
			  }
			}
                //COALISION OBJETOS
                
               
                Iterator<Objeto>objet= panel.objetos.iterator();
                while(objet.hasNext()){
                    Objeto temporal= objet.next();
                    if(hitBox.intersects(temporal.hitBox)) {
                        item=temporal;
                          objet.remove();
                        }
                    
                    }
                    
                    
                for(Objeto objeto: panel.objetos){
                        objeto.hitBox.y+=objeto.velocidady;
                        for(Muro muro: panel.suelo){
              if(objeto.hitBox.intersects(muro.hitBox)) {



                  objeto.hitBox.y-=objeto.velocidady;
                  while(!muro.hitBox.intersects(objeto.hitBox)) objeto.hitBox.y+= Math.signum(objeto.velocidady);
                  objeto.hitBox.y-= Math.signum(objeto.velocidady);
                                  objeto.x=0;
                                  objeto.y=800;
              }
            }

                    }


                
                
                
                
                //FIN COALISION
                
                //TURBO
                if(recarga_turbo>=100){
                    recarga_turbo=100;
                }
                if(recarga_turbo<=-20){
                    recarga_turbo=-20;
                }
		
                if(recarga_turbo<0){
                    recarga_turbo+=0.20;
                }else{
                    recarga_turbo+=1;
                }
                
                //FIN TURBO
		
		if(velocidady>20){
                    velocidady=20;
                }
		
		if(y>panel.alto) {
                    if(this.vidas==1)gameOver=true;
                    else{
                        this.x=200;
                        this.y=480;
                        panel.camara=0;
                        panel.camara1=200;
                        this.vidas-=1;
                        }
                    
		}

		if(!gameOver){
                System.out.println(vidas);}
		
		x+=velocidadx;
		y+=velocidady;
		
		hitBox.x=x;
		hitBox.y=y;

	}
        
        public void activo(){
            //MOVIMIENTO CON SKIN DEL PERSONAJE

            




            if(izquierda && derecha || !izquierda && !derecha){
                velocidadx*=0.95;
                Incremento_normal = 0;
            }
		
                else {
                        if(derecha && !izquierda){
                        direccion="Derecha";
                        velocidadx=5;
                        }
                        if(!derecha && izquierda){
                        direccion="Izquierda";
                        velocidadx=-5;
                        }
                        Incremento_aumento++;
                }
                
         

                for(Muro muro: panel.suelo){
                        hitBox.y+=1;
			  if(muro.hitBox.intersects(hitBox)) {
                            saltar="";
			  }
                        hitBox.y-=1;
		   
                        }
                panel.imgs = new ImageIcon(getClass().getResource("/Imagenes/"+this.skin+"/"+this.skin + saltar + direccion+".png"));
                panel.n_vidas=new ImageIcon(getClass().getResource("/Imagenes/numeros.png"));
                panel.personaje=new ImageIcon(getClass().getResource("/Imagenes/"+this.skin+"/"+this.skin+"Derecha.png"));
                saltar="Saltar";
                if(turbo) {
                    
                    if(recarga_turbo>=0 && Incremento_normal!=0){
                        velocidadx+=Math.signum(velocidadx)*4;
                        if(velocidadx>20) velocidadx=20;
                        if(velocidadx<-20) velocidadx=-20;
                        Incremento_turbo=7;
                        saltar="";
                    }  
		recarga_turbo-=2;	
		}
		
		
		//FIN MOVIMIENTO CON SKIN DEL PERSONAJE
		
			
		// ACCION SALTAR	
		if(arriba){
                   
		  hitBox.y++;
                  
		  for(Muro muro: panel.suelo){

			  if(muro.hitBox.intersects(hitBox)) {
			  velocidady=-9;                  
                        
                        }             
		   
		  }
                   
                   hitBox.y--; 
                   
                 
                  if(velocidady==-7){
                        Incremento_normal=0;
                        }
                        
                     }

                     //FIN ACCION SALTAR
                        

                    //INCREMENTO AUMENTOOOOO
                    if(Incremento_aumento>=6){
                        Incremento_aumento=0;
                        Incremento_normal++;
                    }
                    if(Incremento_normal>=6){
                        Incremento_normal=1;
                    }

                     
                     Incremento_total=Incremento_normal+Incremento_turbo;
                     Incremento_turbo=0;
                     
                     if(tirar && item!=null){
                        panel.objetos.add(item);
                        item.tirar(x,y);
                        item=null;
   
                    }
        }
                //METODO TIRAR
                
                    
}

		

                
             
        
