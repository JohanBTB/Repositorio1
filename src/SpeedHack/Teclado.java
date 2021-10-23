/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpeedHack;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 *
 * @author toshiba
 */
public class Teclado extends KeyAdapter{
	Panel panel;
	
	public Teclado(Panel panel) {
		this.panel=panel;
	}
	public void keyPressed(KeyEvent e) {
		panel.keyPressed(e);
	}
	public void keyReleased(KeyEvent e) {
		panel.keyReleased(e);
	}
}
