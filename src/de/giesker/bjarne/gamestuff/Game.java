package de.giesker.bjarne.gamestuff;

import javax.swing.JLabel;

import de.giesker.bjarne.nflcalculator.Main;
import de.giesker.bjarne.util.MyLinkedList;

@SuppressWarnings("serial")
public class Game extends JLabel {

	private Team teamOne, teamTwo;
	private short scoreOne, scoreTwo;

	public Game(Team team1, Team team2, short score1, short score2) {

		this.teamOne = team1;
		this.teamTwo = team2;

		if (score1 > 255) {
			this.scoreOne = (short) 255;
		} else {
			this.scoreOne = score1;
		}
		if (score2 > 255) {
			this.scoreTwo = (short) 255;
		} else {
			this.scoreTwo = score2;
		}
	}

	public Game(String gameName) {
		this.teamOne = Team.values()[hex2Dec(gameName.substring(0, 2)) + 1];
		this.scoreOne = this.hex2Dec(gameName.substring(2, 4));
		this.teamTwo = Team.values()[hex2Dec(gameName.substring(4, 6)) + 1];
		this.scoreTwo = this.hex2Dec(gameName.substring(6, 8));
	}

	public Team getTeamOne() {
		return this.teamOne;
	}

	public Team getTeamTwo() {
		return this.teamTwo;
	}

	public short getScoreOne() {
		return this.scoreOne;
	}

	public short getScoreTwo() {
		return this.scoreTwo;
	}

	/**
	 * 
	 * @return Winning Team: Null if Tie
	 */
	public Team getWinner() {
		if (this.scoreOne > this.scoreTwo) {
			return this.teamOne;
		} else if (this.scoreOne < this.scoreTwo) {
			return this.teamTwo;
		} else {
			return null;
		}
	}

	public String toString() {
		return dec2Hex(this.teamOne.index) + dec2Hex(this.scoreOne) + dec2Hex(this.teamTwo.index)
				+ dec2Hex(this.scoreTwo);
	}

	private String dec2Hex(short dec) {
		final char[] dec2Hex = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
				'F' };
		return dec2Hex[dec / 16] + "" + dec2Hex[dec % 16];
	}

	private short hex2Dec(String hex) {
		short result, dec;
		char didget = hex.charAt(0);
		if (didget < 58) {
			dec = (short) (didget - 48);
		} else {
			dec = (short) (didget - 55);
		}
		result = (short) (dec * 16);
		didget = hex.charAt(1);
		if (didget < 58) {
			dec = (short) (didget - 48);
		} else {
			dec = (short) (didget - 55);
		}
		return (short) (result + dec);
	}

	public static MyLinkedList<Game> getGames(Team team) {
		MyLinkedList<Game> games = new MyLinkedList<>();
		short size = (short) Main.allGames.size();
		for (short s = 0; s < size; s++) {
			Game temp = Main.allGames.get(s);
			if (temp.getTeamOne() == team || temp.getTeamTwo() == team) {
				games.add(temp);
			}
		}
		return games;
	}

}
