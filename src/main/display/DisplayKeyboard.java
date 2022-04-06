package main.display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import main.customswing.DefaultDialog;
import main.customswing.DefaultLabel;
import main.util.Keys;

public class DisplayKeyboard extends Canvas{
	private static final long serialVersionUID = 1L;

	public DefaultDialog dialog = new DefaultDialog(1000,300);
	private final JPanel panel = new JPanel();
	
	private ArrayList<DefaultLabel> labels = new ArrayList<DefaultLabel>();
	
	Keys keys = new Keys();
	


	public DisplayKeyboard() {
	
		GridLayout grid = new GridLayout(5,25);
		panel.setLayout(grid);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.setBackground(new Color(0.5f, 0.2f, 1.0f, 0f));

		for (Entry<Integer, String> entry : keys.key.entrySet()) {
			DefaultLabel n = new DefaultLabel(entry.getValue(), entry.getKey(), true);
			panel.add(n);
			labels.add(n);
	    }
	
		dialog.add(panel);
		dialog.setVisible(true);
	

	}
	
	public void change(Integer e) {
		if (labels.stream().anyMatch(i -> i.keycode==e)) {
			for (DefaultLabel dl : labels) {
				if (dl.keycode == e) {
					dl.setBackground(new Color(1.0f, 0.5f, 0.0f, 1.0f));
					dialog.setContentPane(panel);
					break;
				}
			}
		}


	}
	
	public void changeback(Integer e) {
		if (labels.stream().anyMatch(i -> i.keycode==e)) {
			for (DefaultLabel dl : labels) {
				if (dl.keycode == e) {
					dl.setBackground(new Color(0.0f, 0.0f, 0.0f, 1.0f));
					dialog.setContentPane(panel);
					break;
				}
			}
		}
	}

}
