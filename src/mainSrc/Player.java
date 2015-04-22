package mainSrc;


public class Player implements BoardConstants {
	String playerStatus;
	String color;
	private int score;
	int activePieceIndex;
	GamePiece [] pieces;
	
	public Player(String color){
		setColor(color);
		activePieceIndex = 0;
		score =0;
		playerStatus= PLAYER_STATUS_WAITING;
	}
	
	public void setPieces(GamePiece [] pieces){
		this.pieces = pieces;
	}
	public void setColor(String color){
		this.color = color.toUpperCase();
	}
	public void setStatus(String status){
		this.playerStatus = status;
	}
	public void setScore (int score){
		this.score = score;
	}
	public void chosePiece(int pieceIndex ){
		activePieceIndex = pieceIndex;
	}
}
