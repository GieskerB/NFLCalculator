package de.giesker.bjarne.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;

import de.giesker.bjarne.gamestuff.Game;
import de.giesker.bjarne.gamestuff.Team;
import de.giesker.bjarne.nflcalculator.Main;
import de.giesker.bjarne.util.MyLinkedList;

@SuppressWarnings("serial")
class PredictionPage extends Page {

	private Selector selector1, selector2;
	private Selector calculationTypSelector;
	private JLabel iconTeam1, iconTeam2, result;

	PredictionPage(Color color) {
		super(color);

		super.setLayout(null);
		super.setSize(Window.WIDTH, Window.HEIGHT);
		super.setLocation(0, 0);
		super.setOpaque(true);
		super.setBackground(super.color);
		super.setVisible(false);

		this.selector1 = new Selector((byte) 2, super.color);
		this.selector2 = new Selector((byte) 15, super.color);
		this.selector1.addActionListener(this);
		this.selector2.addActionListener(this);
		super.add(this.selector1);
		super.add(this.selector2);

		this.calculationTypSelector = new Selector("Wins", "Points", (byte) 4, super.color);
		this.calculationTypSelector.addActionListener(this);
		super.add(this.calculationTypSelector);

		this.iconTeam1 = new JLabel();
		this.iconTeam2 = new JLabel();
		this.iconTeam1.setIcon(Team.None.getSymbol());
		this.iconTeam2.setIcon(Team.None.getSymbol());
		this.iconTeam1.setSize(7 * Window.UNIT, 7 * Window.UNIT);
		this.iconTeam2.setSize(7 * Window.UNIT, 7 * Window.UNIT);
		this.iconTeam1.setLocation((int) (2.5 * Window.UNIT), (int) (3.5 * Window.UNIT));
		this.iconTeam2.setLocation((int) (15.5 * Window.UNIT), (int) (3.5 * Window.UNIT));
		this.iconTeam1.setOpaque(true);
		this.iconTeam2.setOpaque(true);
		super.add(this.iconTeam1);
		super.add(this.iconTeam2);

		this.result = new JLabel();
		this.result.setSize(4 * Window.UNIT, (int) (1.5 * Window.UNIT));
		this.result.setLocation((int) (10.5 * Window.UNIT), (int) (8.5 * Window.UNIT));
		this.result.setBackground(new Color(0xF1F3F5));
		this.result.setFocusable(false);
		this.result.setOpaque(true);
		this.result.setHorizontalAlignment(JTextField.CENTER);
		this.result.setFont(new Font(Main.favFontName, Font.BOLD, 36));
		this.result.setBorder(BorderFactory.createLineBorder(super.color, 2));
		this.add(result);

	}

	@Override
	protected void reload() {
		this.iconTeam1.setBackground(Team.None.getColor());
		this.iconTeam2.setBackground(Team.None.getColor());
	}

	private void calculateResult() {
		Team leftTeam = this.selector1.getSelectedTeam();
		Team rightTeam = this.selector2.getSelectedTeam();

		String result = "";

		if (leftTeam != rightTeam && leftTeam != Team.None && rightTeam != Team.None) {
			MyLinkedList<Game> allGamesLeftTeam = Game.getGames(leftTeam);
			MyLinkedList<Game> allGamesRightTeam = Game.getGames(rightTeam);

			switch (this.calculationTypSelector.getSelectedType()) {
			case "Wins":
				// Ration from wins to loses between both teams
				short leftTeamWins = 0;
				short leftTeamLosses = 0;
				for (short s = 0; s < allGamesLeftTeam.size(); s++) {
					Game temp = allGamesLeftTeam.get(s);
					if (leftTeam == temp.getWinner()) {
						leftTeamWins++;
					} else {
						leftTeamLosses++;
					}
				}
				short rightTeamWins = 0;
				short rightTeamLosses = 0;
				for (short s = 0; s < allGamesRightTeam.size(); s++) {
					Game temp = allGamesRightTeam.get(s);
					if (rightTeam == temp.getWinner()) {
						rightTeamWins++;
					} else {
						rightTeamLosses++;
					}
				}

				if (leftTeamLosses == 0) {
					leftTeamLosses = 1;
				}
				if (rightTeamLosses == 0) {
					rightTeamLosses = 1;
				}

				float leftTeamRatio = (float) leftTeamWins / (float) leftTeamLosses;
				float rightTeamRatio = (float) rightTeamWins / (float) rightTeamLosses;

				result = this.ratio(leftTeamRatio, rightTeamRatio);
				break;
			case "Points":
				// Pure Point Radio
				short leftGameScore = 0;
				short rightGameScore = 0;
				for (short s = 0; s < allGamesLeftTeam.size(); s++) {
					Game tempLeftGame = allGamesLeftTeam.get(s);
					if (tempLeftGame.getTeamOne() == leftTeam) {
						leftGameScore += tempLeftGame.getScoreOne();
					} else {
						leftGameScore += tempLeftGame.getScoreTwo();
					}
				}

				for (short s = 0; s < allGamesRightTeam.size(); s++) {
					Game tempRightGame = allGamesRightTeam.get(s);
					if (tempRightGame.getTeamOne() == rightTeam) {
						rightGameScore += tempRightGame.getScoreOne();
					} else {
						rightGameScore += tempRightGame.getScoreTwo();
					}
				}

				result = this.ratio(leftGameScore, rightGameScore);
				break;
			}
		}
		this.result.setText(result);
	}

	private String ratio(float wins, float loss) {
		float ratio = wins / (wins + loss);
		ratio = (int) (ratio * 1000);
		ratio /= 10;
		return ratio + "%";
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.selector1) {
			Team team = this.selector1.getSelectedTeam();
			this.iconTeam1.setIcon(team.getSymbol());
			this.iconTeam1.setBackground(team.getColor());
		}
		if (e.getSource() == this.selector2) {
			Team team = this.selector2.getSelectedTeam();
			this.iconTeam2.setIcon(team.getSymbol());
			this.iconTeam2.setBackground(team.getColor());
		}
		calculateResult();
	}
}
