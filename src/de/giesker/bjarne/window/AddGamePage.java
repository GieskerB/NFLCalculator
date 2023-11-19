package de.giesker.bjarne.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import de.giesker.bjarne.gamestuff.Game;
import de.giesker.bjarne.gamestuff.Team;
import de.giesker.bjarne.nflcalculator.Main;

@SuppressWarnings("serial")
class AddGamePage extends Page {

	private JButton submitButton;
	private Selector selector1, selector2;
	private JLabel iconTeam1, iconTeam2;
	private JTextField scoreTeam1, scoreTeam2;

	AddGamePage(Color color) {
		super(color);

		super.setLayout(null);
		super.setSize(Window.WIDTH, Window.HEIGHT);
		super.setLocation(0, 0);
		super.setOpaque(true);
		super.setBackground(super.color);
		super.setVisible(false);

		this.submitButton = new JButton("-Submit-");
		this.submitButton.setSize(3 * Window.UNIT, Window.UNIT);
		this.submitButton.setLocation(11 * Window.UNIT, 12 * Window.UNIT);
		this.submitButton.setBackground(new Color(128, 128, 128));
		this.submitButton.setForeground(new Color(16, 16, 16));
		this.submitButton.setBorder(BorderFactory.createLineBorder(super.color, 4));
		this.submitButton.setFont(new Font(Main.favFontName, Font.ITALIC, 24));
		this.submitButton.setFocusable(false);
		this.submitButton.addActionListener(this);
		super.add(this.submitButton);

		this.selector1 = new Selector((byte) 2, super.color);
		this.selector2 = new Selector((byte) 15, super.color);
		this.selector1.addActionListener(this);
		this.selector2.addActionListener(this);
		super.add(this.selector1);
		super.add(this.selector2);

		this.iconTeam1 = new JLabel();
		this.iconTeam2 = new JLabel();
		this.iconTeam1.setIcon(Team.None.getSymbol());
		this.iconTeam2.setIcon(Team.None.getSymbol());
		this.iconTeam1.setSize(7 * Window.UNIT, 7 * Window.UNIT);
		this.iconTeam2.setSize(7 * Window.UNIT, 7 * Window.UNIT);
		this.iconTeam1.setLocation((int) (2.5 * Window.UNIT), (int) (3.5 * Window.UNIT));
		this.iconTeam2.setLocation((int) (15.5 * Window.UNIT), (int) (3.5 * Window.UNIT));
		this.iconTeam1.setBackground(null);
		this.iconTeam2.setBackground(null);
		this.iconTeam1.setOpaque(true);
		this.iconTeam2.setOpaque(true);
		super.add(this.iconTeam1);
		super.add(this.iconTeam2);

		this.scoreTeam1 = new JTextField();
		this.scoreTeam2 = new JTextField();
		this.scoreTeam1.setSize(2 * Window.UNIT, 1 * Window.UNIT);
		this.scoreTeam2.setSize(2 * Window.UNIT, 1 * Window.UNIT);
		this.scoreTeam1.setLocation(5 * Window.UNIT, 11 * Window.UNIT);
		this.scoreTeam2.setLocation(18 * Window.UNIT, 11 * Window.UNIT);
		this.scoreTeam1.setBackground(new Color(0xF1F3F5));
		this.scoreTeam2.setBackground(new Color(0xF1F3F5));
		this.scoreTeam1.setFocusable(true);
		this.scoreTeam2.setFocusable(true);
		this.scoreTeam1.setOpaque(true);
		this.scoreTeam2.setOpaque(true);
		this.scoreTeam1.setBorder(BorderFactory.createLineBorder(super.color, 4));
		this.scoreTeam2.setBorder(BorderFactory.createLineBorder(super.color, 4));
		this.scoreTeam1.setHorizontalAlignment(JTextField.CENTER);
		this.scoreTeam2.setHorizontalAlignment(JTextField.CENTER);
		this.scoreTeam1.setFont(new Font(Main.favFontName, Font.PLAIN, 24));
		this.scoreTeam2.setFont(new Font(Main.favFontName, Font.PLAIN, 24));
		super.add(this.scoreTeam1);
		super.add(this.scoreTeam2);

	}

	@Override
	protected void reload() {
		this.selector1.setSelectedIndex(0);
		this.selector2.setSelectedIndex(0);
		this.scoreTeam1.setText("");
		this.scoreTeam2.setText("");
		this.iconTeam1.setBackground(Team.None.getColor());
		this.iconTeam2.setBackground(Team.None.getColor());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submitButton) {
			// Create a new Game object from inputs
			Team team1 = selector1.getSelectedTeam();
			Team team2 = selector2.getSelectedTeam();
			try {
				var score1 = Short.valueOf(scoreTeam1.getText());
				var score2 = Short.valueOf(scoreTeam2.getText());

				if (team1 != Team.None && team2 != Team.None && team1 != team2 && score1 >= 0 && score2 >= 0
						&&  score1 < 256 && score2 < 256) {
					Game game = new Game(team1, team2, score1, score2);
					Main.allGames.add(game);
					this.selector1.setSelectedIndex(0);
					this.selector2.setSelectedIndex(0);
					this.scoreTeam1.setText("");
					this.scoreTeam2.setText("");
				}
			} catch (NumberFormatException eNum) {
				// Do nothing!
			}
		}

		if (e.getSource() == this.selector1) {
			Team team = selector1.getSelectedTeam();
			this.iconTeam1.setIcon(team.getSymbol());
			this.iconTeam1.setBackground(team.getColor());
		}
		if (e.getSource() == this.selector2) {
			Team team = selector2.getSelectedTeam();
			this.iconTeam2.setIcon(team.getSymbol());
			this.iconTeam2.setBackground(team.getColor());
		}
	}

}
