package main.customswing;

import java.awt.Color;

import javax.swing.JLabel;

import main.util.Art;

public class DefaultLabel extends JLabel {
	private static final long serialVersionUID = 1L;

	public int reset = 300;
	public int time = reset;
	public int keycode;

	private boolean keyboard = false;
	
	

	public DefaultLabel() {
		settings();
	}
	public DefaultLabel(String e) {
		settings();
		setText(e);
	}
	public DefaultLabel(String e, int keycode, boolean keyboard) {
		this.keyboard = keyboard;
		settings();
		this.keycode = keycode;
		setText(e);
	}

	public void settings() {
		setHorizontalAlignment(JLabel.CENTER);
		if(keyboard) {
			setFont(Art.font_small);	
		}else {
			setFont(Art.font);			
		}
		setForeground(Color.white);
		setBackground(new Color(0.0f, 0.0f, 0.0f, 1.0f));
		setOpaque(true);

	}

}
