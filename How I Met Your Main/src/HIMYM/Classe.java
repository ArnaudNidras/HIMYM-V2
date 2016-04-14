package HIMYM;

public enum Classe {

	WARRIOR("Warrior"),
	PALADIN("Paladin"),
	HUNTER("Hunter"),
	ROGUE("Rogue"),
	PRIEST("Priest"),
	DEATHKNIGHT("Death Knight"),
	SHAMAN("Shaman"),
	MAGE("Mage"),
	WARLOCK("Warlock"),
	MONK("Monk"),
	DRUID("Druid"),
	DEMONHUNTER("Demon Hunter");
	
	private String name;
	private int nbGotKilled;
	private int nbKilled;
		
	Classe(String name){
		
		this.name = name;
		this.nbGotKilled = 0;
		this.nbKilled = 0;
		
	}
	
	public String getName(){
		
		return name;
		
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
