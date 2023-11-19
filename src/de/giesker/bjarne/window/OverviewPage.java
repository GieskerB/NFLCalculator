package de.giesker.bjarne.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.giesker.bjarne.gamestuff.Game;
import de.giesker.bjarne.gamestuff.UndoGame;
import de.giesker.bjarne.nflcalculator.Main;
import de.giesker.bjarne.util.MyStack;

@SuppressWarnings("serial")
class OverviewPage extends Page {

	private JButton pageUp, pageDown, undo;
	private JLabel pageLabel;
	private JButton[] delelteButtons;
	private JPanel[] gameTable;
	private JLabel[][] gameLabels;
	private final static byte PAGE_SIZE = 6;
	private byte currentPage = 1;
	private MyStack<UndoGame> undoStack;

	OverviewPage(Color color) {
		super(color);

		super.setLayout(null);
		super.setSize(Window.WIDTH, Window.HEIGHT);
		super.setLocation(0, 0);
		super.setOpaque(true);
		super.setBackground(super.color);
		super.setVisible(false);

		this.pageUp = new JButton(new ImageIcon("pics\\up.png"));
		this.pageUp.setSize(2 * Window.UNIT, 2 * Window.UNIT);
		this.pageUp.setLocation(21 * Window.UNIT, 3 * Window.UNIT);
		this.pageUp.setBackground(new Color(127, 127, 127));
		this.pageUp.setBorder(BorderFactory.createLineBorder(super.color, 4));
		this.pageUp.setFocusable(false);
		this.pageUp.addActionListener(this);
		super.add(this.pageUp);

		this.pageDown = new JButton(new ImageIcon("pics\\down.png"));
		this.pageDown.setSize(2 * Window.UNIT, 2 * Window.UNIT);
		this.pageDown.setLocation(21 * Window.UNIT, 10 * Window.UNIT);
		this.pageDown.setBackground(new Color(127, 127, 127));
		this.pageDown.setBorder(BorderFactory.createLineBorder(super.color, 4));
		this.pageDown.setFocusable(false);
		this.pageDown.addActionListener(this);
		super.add(this.pageDown);

		this.pageLabel = new JLabel("1/1");
		this.pageLabel.setSize(2 * Window.UNIT, Window.UNIT);
		this.pageLabel.setLocation(21 * Window.UNIT, (int) (5.666 * Window.UNIT));
		this.pageLabel.setBackground(new Color(127, 127, 127));
		this.pageLabel.setOpaque(true);
		this.pageLabel.setHorizontalAlignment(JLabel.CENTER);
		this.pageLabel.setFont(new Font(Main.favFontName, Font.PLAIN, 24));
		this.pageLabel.setBorder(BorderFactory.createLineBorder(super.color, 4));
		this.pageLabel.setFocusable(false);
		super.add(this.pageLabel);

		this.undo = new JButton(new ImageIcon("pics\\undo.png"));
		this.undo.setSize(2 * Window.UNIT, 2 * Window.UNIT);
		this.undo.setLocation(21 * Window.UNIT, (int) (7.333 * Window.UNIT));
		this.undo.setFocusable(false);
		this.undo.setBackground(new Color(127, 127, 127));
		this.undo.setBorder(BorderFactory.createLineBorder(super.color, 4));
		this.undo.addActionListener(this);
		super.add(this.undo);

		this.gameTable = new JPanel[PAGE_SIZE];
		for (byte row = 0; row < PAGE_SIZE; row++) {
			this.gameTable[row] = new JPanel();
			this.gameTable[row].setSize(18 * Window.UNIT, 2 * Window.UNIT);
			this.gameTable[row].setLocation(Window.UNIT, (int) (((row + 0.65) * 2.1) * Window.UNIT));
			this.gameTable[row].setOpaque(true);
			this.gameTable[row].setVisible(false);
			this.gameTable[row].setLayout(null);
			super.add(this.gameTable[row]);
		}

		this.gameLabels = new JLabel[PAGE_SIZE][2];
		for (byte row = 0; row < PAGE_SIZE; row++) {
			for (byte column = 0; column < 2; column++) {
				this.gameLabels[row][column] = new JLabel();
				this.gameLabels[row][column].setSize(9 * Window.UNIT, 2 * Window.UNIT);
				this.gameLabels[row][column].setLocation(column * 9 * Window.UNIT, 0);
				this.gameLabels[row][column].setBackground(new Color(0x00338D));
				this.gameLabels[row][column].setForeground(new Color(0, 0, 0));
				this.gameLabels[row][column].setOpaque(true);
				this.gameLabels[row][column].setFont(new Font(Main.favFontName, Font.PLAIN, 16));
				if (column == 0) {
					this.gameLabels[row][column].setHorizontalTextPosition(JLabel.RIGHT);
					this.gameLabels[row][column].setHorizontalAlignment(JLabel.RIGHT);
				} else {
					this.gameLabels[row][column].setHorizontalTextPosition(JLabel.LEFT);
					this.gameLabels[row][column].setHorizontalAlignment(JLabel.LEFT);
				}
				this.gameTable[row].add(this.gameLabels[row][column]);
			}
		}

		this.delelteButtons = new JButton[PAGE_SIZE];
		for (byte row = 0; row < PAGE_SIZE; row++) {
			this.delelteButtons[row] = new JButton(new ImageIcon("pics\\trash.png"));
			this.delelteButtons[row].setSize(Window.UNIT, 2 * Window.UNIT);
			this.delelteButtons[row].setLocation(19 * Window.UNIT, (int) (((row + 0.65) * 2.1) * Window.UNIT));
			this.delelteButtons[row].setFocusable(false);
			this.delelteButtons[row].setBackground(new Color(0xC60C30));
			this.delelteButtons[row].setBorder(BorderFactory.createLineBorder(new Color(0xC60C30), 1, true));
			this.delelteButtons[row].addActionListener(this);
			super.add(this.delelteButtons[row]);
		}

		this.undoStack = new MyStack<>();
	}

	@Override
	protected void reload() {
		this.currentPage = 1;
		this.pageUp.setEnabled(false);
		this.pageDown.setEnabled(PAGE_SIZE < Main.allGames.size());
		this.undo.setEnabled(false);
		this.undoStack.clear();
		this.loadPage();
	}

	private void loadPage() {
		final short totalGames = (short) Main.allGames.size();

		// Reset all JPanels
		for (JPanel jP : this.gameTable) {
			jP.setVisible(false);
		}
		for (JButton jB : this.delelteButtons) {
			jB.setVisible(false);
		}

		// Refill only thouse which need to be filled
		short begIndex = (short) ((this.currentPage - 1) * PAGE_SIZE);
		for (short s = begIndex; s < totalGames && s < PAGE_SIZE + begIndex; s++) {
			if (!Main.allGames.isEmpty()) {
				Game game = Main.allGames.get(s);

				// Load Team One's Image, Name and Score:
				this.gameLabels[s - begIndex][0].setIcon(new ImageIcon(game.getTeamOne().getSymbol().getImage()
						.getScaledInstance(2 * Window.UNIT, 2 * Window.UNIT, Image.SCALE_SMOOTH)));
				this.gameLabels[s - begIndex][0].setText(game.getTeamOne().getName() + " " + game.getScoreOne() + " v");

				// Load Team Two's Iamge, Score and Name:
				this.gameLabels[s - begIndex][1].setIcon(new ImageIcon(game.getTeamTwo().getSymbol().getImage()
						.getScaledInstance(2 * Window.UNIT, 2 * Window.UNIT, Image.SCALE_SMOOTH)));
				this.gameLabels[s - begIndex][1].setText("s " + game.getScoreTwo() + " " + game.getTeamTwo().getName());

				// Display them
				this.gameTable[s - begIndex].setVisible(true);
				this.delelteButtons[s - begIndex].setVisible(true);
			}
			byte maxPage = (byte) (((Main.allGames.size() - 1) / PAGE_SIZE) + 1);
			this.pageLabel.setText(this.currentPage + "/" + maxPage);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object scource = e.getSource();
		// Scroll a page down
		if (scource == this.pageDown && this.currentPage * PAGE_SIZE < Main.allGames.size()) {
			this.currentPage++;
			this.pageUp.setEnabled(true);
			this.pageDown.setEnabled((this.currentPage) * PAGE_SIZE < Main.allGames.size());
			this.loadPage();
		}

		// Scroll a page up
		if (scource == this.pageUp && this.currentPage > 1) {
			this.currentPage--;
			if (this.currentPage == 1) {
				this.pageUp.setEnabled(false);
			}
			this.pageDown.setEnabled((this.currentPage) * PAGE_SIZE < Main.allGames.size());
			this.loadPage();
		}

		// Delete a Game from the list
		for (byte b = 0; b < PAGE_SIZE; b++) {
			if (scource == this.delelteButtons[b]) {
				short deleteIndex = (short) ((this.currentPage - 1) * PAGE_SIZE + b);
				this.undoStack.push(new UndoGame(Main.allGames.get(deleteIndex), deleteIndex));
				Main.allGames.remove(deleteIndex);
				if (Main.allGames.size() <= (this.currentPage - 1) * PAGE_SIZE && this.currentPage < 1) {
					this.currentPage--;
				}

				if (this.currentPage == 1) {
					this.pageUp.setEnabled(false);
				}
				this.pageDown.setEnabled((this.currentPage) * PAGE_SIZE < Main.allGames.size());

				this.undo.setEnabled(true);
				this.loadPage();
			}
		}

		// Undo the last delition
		if (scource == this.undo) {
			UndoGame undoTask = this.undoStack.pop();
			this.undo.setEnabled(!this.undoStack.isEmpty());
			Main.allGames.add(undoTask.getIndex(), undoTask.getGame());
			this.pageDown.setEnabled(Main.allGames.size() > this.currentPage * PAGE_SIZE);
			this.loadPage();
		}

	}
}
