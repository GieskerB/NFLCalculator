package de.giesker.bjarne.window;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
abstract class Page extends JPanel implements ActionListener {

	protected Color color;

	Page(Color color) {
		this.color = color;
	}

	/**
	 * Reloads a page after revisiting
	 */
	protected abstract void reload();

	/**
	 * Sets the visibility to given parameter b
	 * 
	 * @param b: boolean -> Visible or not
	 */
	protected void display(boolean b) {
		super.setVisible(b);
	}

}
