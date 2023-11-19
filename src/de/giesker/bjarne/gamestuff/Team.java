package de.giesker.bjarne.gamestuff;

import java.awt.Color;

import javax.swing.ImageIcon;

public enum Team {

	// Empty Field:
	None(Conference.None, (byte) -1, "None", "", null), // Array Index 0

	// All NFL Teams:
	ARIZONA_CARDINALS(Conference.AFC, (byte) 0, "Arizona", "Cardinals", new Color(151, 35, 63)), // Array Index 1
	ATLANTA_FALCONS(Conference.NFC, (byte) 1, "Atlanta", "Falcons", new Color(167, 25, 48)), // ...
	BALTIMORE_RAVENS(Conference.AFC, (byte) 2, "Baltimore", "Ravens", new Color(36, 23, 115)),
	BUFFALO_BILLS(Conference.AFC, (byte) 3, "Buffalo", "Bills", new Color(0, 51, 141)),
	CAROLINA_PANTHERS(Conference.NFC, (byte) 4, "Carolina", "Panthers", new Color(0, 133, 202)),
	CHICAGO_BEARS(Conference.NFC, (byte) 5, "Chicago", "Bears", new Color(11, 22, 42)),
	CINCINNATI_BENGALS(Conference.AFC, (byte) 6, "Cincinnati", "Bengals", new Color(251, 79, 20)),
	CLEVELAND_BROWNS(Conference.AFC, (byte) 7, "Cleveland", "Browns", new Color(255, 60, 0)),
	DALLAS_COWBOYS(Conference.NFC, (byte) 8, "Dallas", "Cowboys", new Color(0, 34, 68)),
	DENVER_BRONCOS(Conference.AFC, (byte) 9, "Denver", "Broncos", new Color(0, 34, 68)),
	DETROIT_LIONS(Conference.NFC, (byte) 10, "Detroit", "Lions", new Color(0, 118, 182)),
	GREEN_BAY_PACKERS(Conference.NFC, (byte) 11, "Green Bay", "Packers", new Color(32, 55, 49)),
	HOUSTON_TEXANS(Conference.AFC, (byte) 12, "Houston", "Texans", new Color(3, 32, 47)),
	INDIANAPOLIS_COLTS(Conference.AFC, (byte) 13, "Indianapolis", "Colts", new Color(0, 44, 95)),
	JACKSONVILLE_JAGUARS(Conference.AFC, (byte) 14, "Jacksonville", "Jaguars", new Color(159, 121, 44)),
	KANSAS_CITY_CHIEFS(Conference.AFC, (byte) 15, "Kansas City", "Chiefs", new Color(227, 24, 55)),
	LAS_VEGAS_RAIDERS(Conference.AFC, (byte) 16, "Las Vegas", "Raiders", new Color(0, 0, 0)),
	LOS_ANGELES_CHARGERS(Conference.AFC, (byte) 17, "Los Angeles", "Chargers", new Color(0, 42, 94)),
	LOS_ANGELES_RAMS(Conference.NFC, (byte) 18, "Los Angeles", "Rams", new Color(0, 34, 68)),
	MIAMI_DOLPHINS(Conference.AFC, (byte) 19, "Miami", "Dolphins", new Color(0, 142, 151)),
	MINNESOTA_VIKINGS(Conference.NFC, (byte) 20, "Minnesota", "Vikings", new Color(79, 38, 131)),
	NEW_ENGLAND_PATRIOTS(Conference.AFC, (byte) 21, "New England", "Patriots", new Color(0, 34, 68)),
	NEW_ORLEANS_SAINTS(Conference.NFC, (byte) 22, "New Orleans", "Saints", new Color(159, 137, 88)),
	NEW_YORK_GIANTS(Conference.NFC, (byte) 23, "New York", "Giants", new Color(11, 34, 101)),
	NEW_YORK_JETS(Conference.AFC, (byte) 24, "New York", "Jets", new Color(0, 63, 45)),
	PHILADELPHIA_EAGLES(Conference.NFC, (byte) 25, "Philadelphia", "Eagles", new Color(0, 76, 84)),
	PITTSBURGH_STEELERS(Conference.AFC, (byte) 26, "Pittsburgh", "Steelers", new Color(0, 0, 0)),
	SANFRANCISCO_49ERS(Conference.NFC, (byte) 27, "San Francisco", "49ers", new Color(170, 0, 0)),
	SEATTLE_SEAHAWKS(Conference.NFC, (byte) 28, "Seattle", "Seahawks", new Color(0, 34, 68)),
	TAMPA_BAY_BUCCANEERS(Conference.NFC, (byte) 29, "Tampa Bay", "Buccaneers", new Color(213, 10, 10)),
	TENNESSEE_TITANS(Conference.AFC, (byte) 30, "Tennessee", "Titans", new Color(0, 34, 68)),
	WASHINGTON_FOOTBALL_TEAM(Conference.NFC, (byte) 31, "Washington", "Football Team", new Color(90, 20, 20));

	// Attributes of each Team

	byte index;
	Conference conference;
	ImageIcon symbol;
	Color color;
	String cityName, teamName, name;

	// Constructor

	Team(Conference con, byte in, String cN, String tN, Color col) {
		this.conference = con;
		this.index = in;
		this.cityName = cN;
		this.teamName = tN;
		this.name = cN + " " + tN;
		this.color = col;
		this.symbol = new ImageIcon("pics\\" + this.cityName + " " + this.teamName + ".png");
	}

	protected static Team getTeamFromString(String teamName) {
		final Team[] allTeams = Team.values();
		for (byte b = 0; b < allTeams.length; b++) {
			if (teamName.equals(allTeams[b].name)) {
				return allTeams[b];
			}
		}
		return Team.None;
	}

	public byte getIndex() {
		return index;
	}

	public Conference getConference() {
		return conference;
	}

	public ImageIcon getSymbol() {
		return symbol;
	}

	public Color getColor() {
		return color;
	}

	public String getCityName() {
		return cityName;
	}

	public String getTeamName() {
		return teamName;
	}

	public String getName() {
		return name;
	}

}

enum Conference {

	// Both Conferences
	None("None", ""), AFC("AFC", "American Football Conference"), NFC("NFC", "National Football Conference");

	// Attributes of each conferences

	ImageIcon symbol;
	String code, name;

	// Constructor

	Conference(String c, String n) {
		this.code = c;
		this.name = n;
		this.symbol = new ImageIcon("pics\\" + this.code + ".png");
	}
}
