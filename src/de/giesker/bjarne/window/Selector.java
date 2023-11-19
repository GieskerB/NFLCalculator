package de.giesker.bjarne.window;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;

import de.giesker.bjarne.gamestuff.Team;
import de.giesker.bjarne.nflcalculator.Main;

@SuppressWarnings("serial")
final class Selector extends JComboBox<String> {

	private static Color backgroundColor = new Color(128, 128, 128), foregroundColor = new Color(16, 16, 16);
	private static Font font = new Font(Main.favFontName, Font.PLAIN, 24);
	private static Team[] teams = Team.values();

	Selector(byte xLocation, Color borderColor) {
		for (Team team : teams) {
			super.addItem(team.getName());
		}

		super.setSize(8 * Window.UNIT, Window.UNIT);
		super.setLocation(xLocation * Window.UNIT, 2 * Window.UNIT);
		super.setFocusable(true);
		super.setBackground(backgroundColor);
		super.setForeground(foregroundColor);
		super.setFont(font);
		super.setOpaque(true);
		super.setBorder(BorderFactory.createLineBorder(borderColor, 4));
	}

	Selector(String firstStatement, String secondStatement, byte yLocation, Color borderColor) {
		super.addItem(firstStatement);
		super.addItem(secondStatement);

		super.setSize(3 * Window.UNIT, 1 * Window.UNIT);
		super.setLocation(11 * Window.UNIT, yLocation * Window.UNIT);
		super.setFocusable(true);
		super.setBackground(backgroundColor);
		super.setForeground(foregroundColor);
		super.setFont(font);
		super.setOpaque(true);
		super.setBorder(BorderFactory.createLineBorder(borderColor, 4));

	}

	protected Team getSelectedTeam() {
		return teams[super.getSelectedIndex()];
	}

	protected String getSelectedType() {
		return (String) super.getSelectedItem();
	}

}
