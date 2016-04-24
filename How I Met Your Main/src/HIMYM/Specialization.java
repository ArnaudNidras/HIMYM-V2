package HIMYM;

public enum Specialization {

	ARMS("Arms", "DPS"),
	FURY("Fury", "DPS"),
	PROTECTIONW("Protection", "Tank"),
	
	HOLYPA("Holy", "Healer"),
	PROTECTIONP("Protection", "Tank"),
	RETRIBUTION("Retribution", "DPS"),
	
	BEASTMASTERY("Beast Mastery", "DPS"),
	MARKSMANSHIP("Marksmanship", "DPS"),
	SURVIVAL("Survival", "DPS"),
	
	ASSASSINATION("ASSASSINATION", "DPS"),
	COMBAT("Combat", "DPS"),
	SUBTLETY("Subtlety", "DPS"),
	
	DISCIPLINE("Discipline", "Healer"),
	HOLYPR("Holy", "Healer"),
	SHADOW("Shadow", "DPS"),
	
	BLOOD("Blood", "Tank"),
	FROSTD("Frost", "DPS"),
	UNHOLY("Unholy", "DPS"),
	
	ELEMENTAL("Elemental", "DPS"),
	ENHANCEMENT("Enhancement", "DPS"),
	RESTORATIONS("Restoration", "Healer"),
	
	ARCANE("Arcane", "DPS"),
	FIRE("Fire", "DPS"),
	FROSTM("Frost", "DPS"),
	
	AFFLICTION("Affliction", "DPS"),
	DEMONOLOGY("Demonology", "DPS"),
	DESTRUCTION("Destruction", "DPS"),
	
	BREWMASTER("Brewmaster", "Tank"),
	MISTWEAVER("Mistweaver", "Healer"),
	WINDWALKER("Windwalker", "DPS"),
	
	BALANCE("Balance", "DPS"),
	FERAL("Feral", "DPS"),
	GUARDIAN("Guardian", "Tank"),
	RESTORATIOND("Restoration", "Healer"),
	
	HAVOC("Havoc", "DPS"),
	VENGEANCE("Vengeance", "Tank");
	
	private String name;
	private String role;
	private int nbGotKilled;
	private int nbKilled;
	
	Specialization(String name, String role){
		
		this.name = name;
		this.role = role;
		this.nbGotKilled = 0;
		this.nbKilled = 0;
		
	}
	
	public String getName(){
		
			return name;
		
		}

	public String getRole(){
		
			return role;
		
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
