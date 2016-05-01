package HIMYM;

public enum Race {
	
	HUMAN("Human"),
	DWARF("Dwarf"),
	GNOME("Gnome"),
	NIGHTELF("Night Elf"),
	DRAENEI("Draenei"),
	WORGEN("Worgen"),
	PANDAREN("Pandaren"),
	ORC("Orc"),
	UNDEAD("Undead"),
	TAUREN("Tauren"),
	TROLL("Troll"),
	BLOODELF("Blood Elf");
	
	private String name;
	private int nbGotKilled;
	private int nbKilled;
		
	Race(String name){
		
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
