package main.customswing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;

import main.util.Art;

public class DefaultDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	private int width = 0; // 400
	private int height = 0; // 300

	private int frameX, frameY;

	public DefaultDialog() {
		settings();
	}

	public DefaultDialog(int width, int height) {
		this.width = width;
		this.height = height;
		settings();
	}

	public void settings() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
		setSize(width, height);
		setLocationRelativeTo(null);
		setBackground(Art.color);
		setAlwaysOnTop(true);
		/*
		 * addWindowListener(new WindowAdapter() {
		 * 
		 * @Override public void windowClosing(WindowEvent e) { System.exit(0); } });
		 */
		Movement();
	}

	public void Movement() {
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				frameX = e.getXOnScreen() - getX();
				frameY = e.getYOnScreen() - getY();
			}
		});
		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				setLocation(e.getXOnScreen() - frameX, e.getYOnScreen() - frameY);
			}
		});
	}

}
