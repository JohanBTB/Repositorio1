/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpeedHack;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import Interfases.ChooseJFrame;


/**
 *
 * @author francesco sanchez
 */
public class Panel extends javax.swing.JPanel implements ActionListener{

	public Jugador jugador;
	int ancho,alto;
	Timer gameTimer;
         //Animacion
        BufferedImage bi;
        ImageIcon imgs;
        ImageIcon n_vidas;
        ImageIcon personaje;
        
        //IMAGENES-SKINS
        public String skin_background="background2.png";
        ImageIcon gameover_bg=(new ImageIcon(getClass().getResource("/Imagenes/GameOver.jpg")));
        ImageIcon huevo_skin=(new ImageIcon(getClass().getResource("/Imagenes/Objetos/huevo.png")));
        ImageIcon muro_skin=(new ImageIcon(getClass().getResource("/Imagenes/Muros/PASTO2.png")));
        ImageIcon background=(new ImageIcon(getClass().getResource("/Imagenes/"+skin_background)));
        int camara,camara1;
        //
        
	ArrayList<Muro> suelo= new ArrayList<Muro>();
        ArrayList<Muro> subsuelo= new ArrayList<Muro>();
        ArrayList<Objeto> objetos= new ArrayList<Objeto>();
        ArrayList<Objeto> objetos_tirados= new ArrayList<Objeto>();
	
	
	public Panel(int ancho,int alto) {
                Panel panel= this;
		this.ancho=ancho;
                this.alto=alto;
		jugador= new Jugador(200,480,3,panel);
                generarMuros();
		camara=0;
                camara1=0;
		
                generarObjetos();
		
		gameTimer=new Timer();
		gameTimer.schedule(new TimerTask(){
			public void run() {
                                if(!jugador.gameOver){
                                    jugador.pasivo();
                                    jugador.activo();
                                }else{
                                if(jugador.tirar){
                                    jugador= new Jugador(200,400,3,panel);
                                    generarObjetos();
                                    generarMuros();
                                    jugador.gameOver=false;
                                    }
                                    
                               
                               }    
                           repaint();

                                
				
                                
			}
		},0,13);
		
	}
	
	public void paint(Graphics g) {
		if(!jugador.gameOver){
                super.paint(g);//Pinta encima del background
		int mx = jugador.Incremento_total*40;
                //Lo que toma cada cuadro en sobreponerse.
                int my = 0;
		Graphics2D gtd= (Graphics2D) g;
                
                //SCROLLING-BACKGROUND
                if(jugador.x<=400 || jugador.x>=3600){
                    if(camara<0 && camara>-410)          camara=0;
                    if(camara<-3100 && camara>-3700)        camara=-3600;
                    
                    camara+=Integer.signum(camara)*(-400);
                    camara1=jugador.x+((-1+Integer.signum(401-jugador.x))*1600);
                    
                    gtd.drawImage(background.getImage(),camara,0,this);
                    
                    gtd.drawImage(imgs.getImage(), camara1-6, jugador.y-6, camara1+40, 60+jugador.y, mx, my, mx+40, my+60, this);
                    for(Muro muro: suelo)                    muro.draw(gtd,camara,muro_skin,this);
                    for(Muro submuro: subsuelo)              submuro.draw(gtd,camara,muro_skin,this);
                    for(Objeto objeto: objetos)              objeto.draw(gtd,camara,huevo_skin , this);
                
                }else{
                    camara=-jugador.x;
                    
                    gtd.drawImage(background.getImage(),camara+401,0,null);
                    gtd.drawImage(imgs.getImage(), 400-6, jugador.y-6, 440, 60+jugador.y, mx, my, mx+40, my+60, this);
                    
                    for(Muro muro:suelo)muro.draw(gtd,camara+401,muro_skin,this);
                    for(Muro submuro:subsuelo)submuro.draw(gtd,camara+401,muro_skin,this);
                    for(Objeto objeto: objetos) objeto.draw(gtd,camara+401,huevo_skin , this);
                }
                    gtd.drawImage(personaje.getImage(), 850,10,890,70,0,0,40,60,this);
                    gtd.drawImage(n_vidas.getImage(), 900, 10, 940, 60, 4+jugador.vidas*80, 3, 84+jugador.vidas*80, 100, this);
                }else{
                        Graphics2D gtd= (Graphics2D) g;
                     gtd.drawImage(gameover_bg.getImage(),0,0,null);

                    }


                
	}
	
	

	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar()=='a') jugador.izquierda=true;
				
		if(e.getKeyChar()=='w') jugador.arriba=true;
				
		
		if(e.getKeyChar()=='s') jugador.abajo=true;
				
		
		if(e.getKeyChar()=='d')jugador.derecha=true;
		if(e.getKeyChar()=='p')jugador.turbo=true;
                if(e.getKeyChar()=='o')jugador.tirar=true;
				
		
	}

	public void keyReleased(KeyEvent e) {

		if(e.getKeyChar()=='a') jugador.izquierda=false;
		
		if(e.getKeyChar()=='w') jugador.arriba=false;
				
		
		if(e.getKeyChar()=='s') jugador.abajo=false;
				
		
		if(e.getKeyChar()=='d')jugador.derecha=false;
		if(e.getKeyChar()=='p')jugador.turbo=false;
                if(e.getKeyChar()=='o')jugador.tirar=false;
	}
	
	public void generarMuros() {
            suelo.clear();
            subsuelo.clear();
            int aleatorio;
            int aleatorio2;
            int aleatorio3;
            int aleatorio4;
                    
		for(int i=0;i<=4200;i+=60){
                    aleatorio=(int)(Math.random()*4);
                    aleatorio2=(int)(Math.random()*4);
                    aleatorio3=(int)(Math.random()*8);
                    aleatorio4=(int)(Math.random()*8);
                    
                        if(aleatorio==1 || i==180){
                        
                        suelo.add(new Muro(i,550,60,60,aleatorio2,0));
                        subsuelo.add(new Muro(i,610,60,60,1,1));
                        }
                        if(aleatorio3==2 || i==120){
                        
                        suelo.add(new Muro(i,490,20,20,aleatorio2,0));

                        }
                        if(aleatorio4==3 || i==120){
                        
                        suelo.add(new Muro(i,420,20,20,aleatorio2,0));

 
                        }
                        
                        }
                       /* if(i!=129 || i!=162){
                        suelo.add(new Muro(i,550,43,43,aleatorio2,0));
                        subsuelo.add(new Muro(i,593,43,43,1,1));
                        }*/
                        
			
                
          
                
	}
        
        public void generarObjetos() {
           
            objetos.add(new Objeto(500,350,30,30));
            objetos.add(new Objeto(600,350,30,30));
            objetos.add(new Objeto(700,350,30,30));
                
		
	}
        
       

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}

