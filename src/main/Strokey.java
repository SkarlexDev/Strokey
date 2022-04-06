package main;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import main.display.DisplayFrame;
import main.display.DisplayKeyboard;
import main.display.DisplayMenu;

public class Strokey implements NativeKeyListener {

	private static DisplayKeyboard keyboard;
	private static DisplayFrame frame;
	private static DisplayMenu menu = new DisplayMenu();

	public boolean[] key = new boolean[68836];
	public int counter = 0;
	public boolean hold = false;

	public String lastKey = "";
	public StringBuilder actionKey = new StringBuilder();

	public static boolean start = false;
	public static boolean isKeyboard = false;

	public static void main(String[] args) {
		
		LogManager.getLogManager().reset();
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);
		try {
			GlobalScreen.registerNativeHook();

		} catch (NativeHookException e) {
			e.printStackTrace();
		}
		GlobalScreen.addNativeKeyListener(new Strokey());
		new DisplayTrayIcon();

	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {

		// System.out.println( "key.put(" + e.getKeyCode() + ',' + '"' +
		// NativeKeyEvent.getKeyText(e.getKeyCode()) + '"' + ')' + ";");
		if (!start) {
			return;
		}
		String keyText = NativeKeyEvent.getKeyText(e.getKeyCode());
		if (e.isActionKey()) {
			hold = true;
			if (!key[e.getKeyCode()]) {
				key[e.getKeyCode()] = true;
				actionKey.append(NativeKeyEvent.getKeyText(e.getKeyCode()).toString() + " + ");
				lastKey = keyText;
			}
			if (isKeyboard) {
				keyboard.change(e.getKeyCode());
			}
		}

		if (!hold) {
			if (!isKeyboard) {
				frame.change(NativeKeyEvent.getKeyText(e.getKeyCode()) + ((counter++ > 0) ? (" x" + counter) : ("")),
						counter);
			} else {
				keyboard.change(e.getKeyCode());
			}
		}
		if (!key[e.getKeyCode()] && hold) {
			if (!isKeyboard) {
				actionKey.append(NativeKeyEvent.getKeyText(e.getKeyCode()).toString());
				frame.change(actionKey.toString(), 0);
				actionKey = new StringBuilder();
			}
			hold = false;
		}

		if (!key[e.getKeyCode()] && !hold) {
			// reset counter
			if (!lastKey.equals(keyText)) {
				reset();
			}
			key[e.getKeyCode()] = true;
			lastKey = keyText;
		}

	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {

		if (!start) {
			return;
		}
		if (key[e.getKeyCode()]) {
			key[e.getKeyCode()] = false;
			reset();
			if (e.isActionKey() && hold) {
				if (!isKeyboard) {
					frame.change(NativeKeyEvent.getKeyText(e.getKeyCode()).toString(), 0);
				}
			}
			if (isKeyboard) {
				keyboard.changeback(e.getKeyCode());
			}
		}

	}

	public static void keyboardCreate() {
		isKeyboard = true;
		if (keyboard == null) {
			keyboard = new DisplayKeyboard();
			start = true;
			menu = null;
		} else {
			System.out.println("Keyboard Already created");
		}

	}

	public static void frameCreate() {
		isKeyboard = false;
		if (frame == null) {
			frame = new DisplayFrame();
			start = true;
			menu = null;
		} else {
			System.out.println("Frame Already created");
		}

	}

	public static void createMenu() {
		start = false;
		isKeyboard = false;
		if (menu == null) {
			if (frame != null) {
				frame.dialog.dispose();
				frame = null;
			}
			if (keyboard != null) {
				keyboard.dialog.dispose();
				keyboard = null;
			}
			menu = new DisplayMenu();
		} else {
			System.out.println("Menu already exist");
		}
	}

	public void reset() {
		counter = 0;
		actionKey = new StringBuilder();
	}

}
