package main.display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import main.customswing.DefaultDialog;
import main.customswing.DefaultLabel;

public class DisplayFrame extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public DefaultDialog dialog = new DefaultDialog(400,300);
	private final JPanel panel = new JPanel();
	private ArrayList<DefaultLabel> labels = new ArrayList<DefaultLabel>();
	private int frameX, frameY;

	private Thread thread;
	private boolean running = false;

	public DisplayFrame() {

		GridLayout grid = new GridLayout(4, 1);
		
		panel.setLayout(grid);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.setBackground(new Color(0.5f, 0.2f, 1.0f, 0f));
		dialog.add(panel);		
		dialog.setVisible(true);
		start();

	}

	// similar to c# void update
	@Override
	public void run() {
		double unprocessedSeconds = 0;
		long prevTime = System.nanoTime();
		double secondsPerTick = 1 / 60.0;

		while (running) {
			long currentTime = System.nanoTime();
			long passedTime = currentTime - prevTime;
			prevTime = currentTime;
			unprocessedSeconds += passedTime / 1000000000.0;
			while (unprocessedSeconds > secondsPerTick) {
				unprocessedSeconds -= secondsPerTick;
				showLifeTime();
			}

		}
	}

	public void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
		System.out.println("running...");
	}

	public void change(String e, int time) {

		try {
			if (time > 1) {
				DefaultLabel df = labels.get(labels.size() - 1);
				df.setText(e);
				df.time = 200;
				panel.add(df);
				dialog.setContentPane(panel);
			} else {
				DefaultLabel n = new DefaultLabel(e);
				labels.add(n);
				for (int i = 0; i < labels.size(); i++) {
					panel.add(labels.get(i));
				}
				resize(5);
				dialog.setContentPane(panel);
			}
		} catch (Exception e2) {
			// TODO: handle exception
		}

	}

	public void showLifeTime() {
		try {
			Iterator<DefaultLabel> i = labels.iterator();
			while (i.hasNext()) {
				DefaultLabel s = i.next();

				if (s.time > 0) {
					s.time -= 5;
					s.setBackground(new Color(0.0f, 0.0f, 0.0f, (float) s.time / 10));

				} else {
					i.remove();
					panel.removeAll();
					labels.trimToSize();
					for (int ii = 0; ii < labels.size(); ii++) {
						panel.add(labels.get(ii));
					}
				}
				dialog.setContentPane(panel);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// resize array
	public void resize(int r) {
		if (labels.size() >= r) {
			panel.removeAll();
			labels.remove(0);
			labels.trimToSize();
			for (int i = 0; i < labels.size(); i++) {
				panel.add(labels.get(i));
			}
		}
	}

	// allow frame to move
	public void Movement() {
		dialog.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				frameX = e.getXOnScreen() - dialog.getX();
				frameY = e.getYOnScreen() - dialog.getY();
			}
		});
		dialog.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				dialog.setLocation(e.getXOnScreen() - frameX, e.getYOnScreen() - frameY);
			}
		});
	}

}