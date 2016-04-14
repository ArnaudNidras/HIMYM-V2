package HIMYM;

public class Fight {
	
	private Player a;
	private Player b;
	private Player winner;
	private Player loser;
	
	private Time time;
	private Place place;
	private String fight_comment;
	private int fight_length;
	
	public Fight(Player a, Player b, Player winner, Player loser, Time time, Place place, String fight_comment, int fight_length){
		
		this.a = a;
		this.b = b;
		this.winner = winner;
		this.loser = loser;
		this.time = time;
		this.place = place;
		this.fight_comment = fight_comment;
		this.fight_length = fight_length;
		
		
	}
	
	public Player getPlayerA(){
		
		return a;
		
	}
	
	public Player getPlayerB(){
		
		return b;
		
	}
	
	public Time getTime(){
		
		return time;
		
	}
	
	public Place getPlace(){
		
		return place;
		
	}
	
	public Player getWinner(){
		
		return winner;
		
	}
	
	public Player getLoser(){
		
		return loser;
		
	}
	
	public void addComment(String comment){
		
		if(this.fight_comment != "") this.fight_comment += "/nNew comment : " + comment;
		else this.fight_comment += "New comment : " + comment;
		
	}
	
	public String fight_comment(){
		
		return fight_comment;
		
	}
	
	public int getFight_length(){
		
		return fight_length;
		
	}

}
