package HIMYM;

public class Guild {

	private String name;
	private int nbGotKilled;
	private int nbKilled;
	
	public Guild(String name, int ngGotKilled, int nbKilled){
		
		this.name = name;
		this.nbGotKilled = nbGotKilled;
		this.nbKilled = nbKilled;
		
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
