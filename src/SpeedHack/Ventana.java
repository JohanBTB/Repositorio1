/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpeedHack;

import java.awt.Color;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

/**
 *
 * @author francesco sanchez
 */
public class Ventana extends JFrame{
    int ANCHO=1000;
    int ALTO=680;
    public Panel panel = new Panel(ANCHO,ALTO);
    public Ventana(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("SpeedHack");
        setSize(ANCHO,ALTO);
        setLocationRelativeTo(null);
        setResizable(false);
        panel.setBackground(Color.white);
        add(panel);
        addKeyListener(new Teclado(panel));
        sonido();
        
    }
    public static void main(String[] args) {
        new Ventana().setVisible(true);
    }
    Clip clip;
    public void sonido(){
        try {
            clip=AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/Musica/MusicaBack.wav")));
            clip.start();
            
        } catch (Exception e) {
        }
    }
    
}
