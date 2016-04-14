package HIMYM;

public class WhispRage {
	
	private int nb;
	private int quality;
	private String comment;
	//private String whisp;
	//lien fichier jpeg à faire plus tard;
	
	public WhispRage(int quality, String comment/*, String whisp*/){
		
		this.nb = 1;
		this.quality = quality;
		this.comment = comment;
		//this.whisp = whisp;
		
	}
	
	public void addNB(){
		
		nb ++;
		
	}
	
	public void setQuality(int quality){
		
		this.quality = quality;
		
	}
	
	public void addComment(String comment){
		
		this.comment += "/nNew comment : " + comment;
		
	}
	
	public int getNB(){
		
		return nb;
		
	}

	public int getQuality(){
		
		return quality;
		
	}
	
	public String getComment(){
		
		return comment;
		
	}
	
}
