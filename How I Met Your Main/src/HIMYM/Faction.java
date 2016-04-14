package HIMYM;

public class Faction {
	
	private String faction;
	private int nbGotKilled;
	private int nbKilled;
	
	public Faction(String faction){
		
		this.faction = faction;
		this.nbGotKilled = 0;
		this.nbKilled = 0;
		
	}
	
	public String getName(){
		
		return faction;
		
	}
	
	public int getGotKilled(){
		
		return nbGotKilled;
		
	}
	
	public int getKilled(){
		
		return nbKilled;
		
	}
	
	public void gotKilled(){
		
		nbGotKilled ++;
		
	}
	
	public void killed(){
		
		nbKilled ++;
		
	}
	
	public float getRatio(){
		
		return nbKilled/nbGotKilled;
		
	}

}
