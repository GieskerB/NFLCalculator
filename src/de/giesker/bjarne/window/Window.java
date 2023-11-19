package de.giesker.bjarne.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.giesker.bjarne.nflcalculator.Main;

@SuppressWarnings("serial")
public class Window extends JFrame implements ActionListener, WindowListener {

	public final static short WIDTH = 1250; /* 25 UNITS */
	public final static short HEIGHT = 750; /* 15 UNITS */
	public final static byte UNIT = 50;

	private JButton[] menuBar;

	private Page page1, page2, page3;
	private Color color1, color2, color3;

	public Window() {
		super.setSize(WIDTH, HEIGHT);
		super.setLayout(null);
		super.setLocationRelativeTo(null);
		super.setResizable(false);
		super.setTitle("NFL Calculator");
		super.setIconImage(new ImageIcon("pics\\Logo.png").getImage());

		super.addWindowListener(this);

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(this);

		this.color1 = new Color(0xC60C30);
		this.color2 = new Color(0xF1F3F5);
		this.color3 = new Color(0x00338D);

		this.buttonInit();

		this.page1 = new AddGamePage(color1);
		this.page2 = new OverviewPage(color2);
		this.page3 = new PredictionPage(color3);

		super.add(page1);
		super.add(page2);
		super.add(page3);

		this.page1.setVisible(true);

		super.setVisible(true);
	}

	private void buttonInit() {
		menuBar = new JButton[3];

		menuBar[0] = new JButton("Add Result");
		menuBar[1] = new JButton("Overview");
		menuBar[2] = new JButton("Prediction");

		for (JButton button : menuBar) {
			button.setSize(WIDTH / 3 - 4, UNIT); // Width = 416 *3 = 1248
			button.setForeground(new Color(16, 16, 16));
			button.setFont(new Font(Main.favFontName, Font.BOLD, 24));
			button.setFocusable(false);
			button.addActionListener(this);
		}

		menuBar[0].setLocation(0, 0);
		menuBar[1].setLocation(WIDTH / 3 - 4, 0);
		menuBar[2].setLocation(2 * WIDTH / 3 - 9, 0);

		menuBar[0].setBackground(this.color1);
		menuBar[1].setBackground(this.color2);
		menuBar[2].setBackground(this.color3);

		menuBar[0].setBorder(BorderFactory.createLineBorder(color1, 5));
		menuBar[1].setBorder(BorderFactory.createLineBorder(color2, 5));
		menuBar[2].setBorder(BorderFactory.createLineBorder(color3, 5));

		for (JButton button : menuBar) {
			this.add(button);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == menuBar[0]) {
			page1.reload();
			page1.display(true);
			page2.display(false);
			page3.display(false);
		}

		if (e.getSource() == menuBar[1]) {
			page2.reload();
			page1.display(false);
			page2.display(true);
			page3.display(false);
		}

		if (e.getSource() == menuBar[2]) {
			page3.reload();
			page1.display(false);
			page2.display(false);
			page3.display(true);
		}

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// Write all the Games into a txt File so it can be accessed later.
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("cache\\gamecach.txt"));
			short size = (short) Main.allGames.size();
			for (short s = 0; s < size; s++) {
				writer.write(Main.allGames.get(s).toString());
			}
			writer.close();
		} catch (IOException e1) {
			// Well now we have a problem...
			e1.printStackTrace();
		}

		System.exit(0);
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

}
