package HIMYM;

public class Guild {

	private String name;
	private int nbGotKilled;
	private int nbKilled;
	
	public Guild(String name){
		
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
