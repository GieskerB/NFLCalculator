package de.giesker.bjarne.gamestuff;

public class UndoGame {

	private Game game;
	private short index;

	public UndoGame(Game game, short index) {
		this.game = game;
		this.index = index;
	}

	public Game getGame() {
		return this.game;
	}

	public short getIndex() {
		return this.index;
	}

}
