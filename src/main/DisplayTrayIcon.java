package main;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;

public class DisplayTrayIcon {

	static TrayIcon trayIcon;
	public DisplayTrayIcon() {
		showTrayIcon();
	}
	
	public void showTrayIcon() {

		if (!SystemTray.isSupported()) {
			System.out.println("System tray is not supported !!! ");
			return;
		}
		
		trayIcon = new TrayIcon(createIcon("/images/31038.png"), "KeyStroke");
		trayIcon.setImageAutoSize(true);

		final SystemTray systemTray = SystemTray.getSystemTray();
		
		PopupMenu trayPopupMenu = new PopupMenu();
		MenuItem action = new MenuItem("Menu");
		action.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Strokey.createMenu();
			}
		});
		trayPopupMenu.add(action);

		MenuItem close = new MenuItem("Close");
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		trayPopupMenu.add(close);

		trayIcon.setPopupMenu(trayPopupMenu);
		
		try {
			systemTray.add(trayIcon);
		} catch (AWTException awtException) {
			awtException.printStackTrace();
		}
		System.out.println("end of main");
	}

	protected static Image createIcon(String path) {
		URL imageUrl = Strokey.class.getResource(path);
		return (new ImageIcon(imageUrl).getImage());

	}
}
