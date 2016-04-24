package HIMYM;

public class Player {

	private String name;
	private Guild guild;
	private Faction faction;
	private Classe classe;
	private Specialization specialization;
	private int nbGotKilled;
	private int nbKilled;
	//private int hpLeft;
	private WhispRage whisprage;
	private int skill; // à rentrer à la main sur l'addon
	private boolean backped;
	private String skill_comment;

	public Player(String name, Guild guild, Faction faction, Classe classe, Specialization specialization, /*int hpLeft,*/
			String comment, int skill, boolean backped) {

		this.name = name;
		this.guild = guild;
		this.faction = faction;
		this.classe = classe;
		this.specialization = specialization;
		this.nbGotKilled = 0;
		this.nbKilled = 0;
		//this.hpLeft = hpLeft;
		this.whisprage = null;
		this.skill = skill;
		this.backped = backped;
		this.skill_comment = comment;

	}

	public String getName() {

		return name;

	}

	public Guild getGuild() {

		return guild;

	}

	public Faction getFaction() {

		return faction;

	}

	public Classe getClasse() {

		return classe;

	}

	public Specialization getSpecialization() {

		return specialization;

	}

	public int getGotKilled() {

		return nbGotKilled;

	}

	public int getKilled() {

		return nbKilled;

	}

	public void gotKilled() {

		nbGotKilled++;

	}

	public void killed() {

		nbKilled++;

	}

	/*public void setHPLeft(int hpLeft) {

		this.hpLeft = hpLeft;

	}

	public int getHPLeft() {

		return hpLeft;

	}*/

	public WhispRage getWhispRage() {

		return whisprage;

	}

	public void setSkill(int skill) {

		this.skill = skill;

	}

	public void setBackped(boolean backped) {

		this.backped = backped;

	}

	public int getSkill() {

		return skill;

	}

	public boolean getBackped() {

		return backped;

	}
	
	public void setWhispRage(int quality, String comment){
		
		if(this.whisprage == null) this.whisprage = new WhispRage(quality, comment);
		else{
			
			whisprage.setQuality(quality);
			whisprage.addComment(comment);
			
		}
		
	}
	
	public void addComment(String comment){
		
		this.skill_comment += "/nNew comment : " + comment;
		
	}
	
	public String getSkill_comment(){
		
		return skill_comment;
		
	}

}
