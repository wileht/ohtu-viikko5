package ohtu;

public class TennisGame {

	private int player1Score = 0;
	private int player2Score = 0;
	private String player1Name;
	private String player2Name;

	public TennisGame(String player1Name, String player2Name) {
		this.player1Name = player1Name;
		this.player2Name = player2Name;
	}

	public void wonPoint(String playerName) {
		if (playerName == player1Name)
			player1Score += 1;
		else if (playerName == player2Name) {
			player2Score += 1;
		}
	}

	public String getScore() {
		if (player1Score == player2Score) {
			return tieScore();
		} else if (player1Score >= 4 || player2Score >= 4) {
			return overFourScore();
		} else {
			return playerScoreName(player1Score) + "-" + playerScoreName(player2Score);
		}
	}

	private String playerScoreName(int playerScore) {
		switch (playerScore) {
		case 0:
			return "Love";
		case 1:
			return "Fifteen";
		case 2:
			return "Thirty";
		case 3:
			return "Forty";
		}
		return null;
	}

	private String overFourScore() {
		int minusResult = player1Score - player2Score;
		if (minusResult == 1)
			return "Advantage player1";
		else if (minusResult == -1)
			return "Advantage player2";
		else if (minusResult >= 2)
			return "Win for player1";
		else if (minusResult <= -2)
			return "Win for player2";
		return null;
	}

	private String tieScore() {
		switch (player1Score) {
		case 0:
			return "Love-All";
		case 1:
			return "Fifteen-All";
		case 2:
			return "Thirty-All";
		case 3:
			return "Forty-All";
		default:
			return "Deuce";

		}
	}
}