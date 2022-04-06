package main.display;

import java.awt.Canvas;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.Main;
import main.customswing.DefaultDialog;
import main.customswing.DefaultLabel;
import main.util.Art;

public class DisplayMenu extends Canvas {
	private static final long serialVersionUID = 1L;

	DefaultDialog menu = new DefaultDialog(300, 300);
	JPanel panel = new JPanel();

	public DisplayMenu() {
		panelSettings();
		buttons();
		menu.add(panel);
		menu.setVisible(true);
	}

	public void buttons() {
		// Keyboard
		JButton displayKeyboard = new JButton("Keyboard");
		displayKeyboard.setFont(Art.font);
		displayKeyboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.keyboardCreate();
				menu.dispose();
			}
		});
		panel.add(displayKeyboard);
		// Keyboard
		JButton displayPress = new JButton("Frame");
		displayPress.setFont(Art.font);
		displayPress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.frameCreate();
				menu.dispose();
			}
		});
		panel.add(displayPress);
		// exit
		JButton exit = new JButton("Exit");
		exit.setFont(Art.font);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel.add(exit);
	}

	public void panelSettings() {
		GridLayout grid = new GridLayout(0, 1);
		DefaultLabel label = new DefaultLabel("Title");
		panel.add(label);
		panel.setLayout(grid);
	}

}
