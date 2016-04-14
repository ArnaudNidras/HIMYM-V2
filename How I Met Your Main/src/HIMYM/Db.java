package HIMYM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Db {

	Connection con;
	String username;
	String password;
	ObjectLists objectLists;
	
	public Db() throws ClassNotFoundException{
		
		this.objectLists = new ObjectLists(this);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
			
			this.username = "HIMYM";
			this.password = "HIMYM"; 
			
			this.con = DriverManager.getConnection("jdbc:oracle:thin:@ns202518.ovh.net:1521:xe",username,password);
			//this.con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:5000:xe",username,password);
			
			
			
			
		}
		
		catch ( SQLException err ) {
		
			System.out.println( err.getMessage( ) );
			
		}
		
		//this.objectLists.loadEverything();
	}
	
	public void connect() throws ClassNotFoundException{
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
			
			//con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe",username,password);
			con = DriverManager.getConnection("jdbc:oracle:thin:@ns202518.ovh.net:1521:xe",username,password);
			
		}
		
		catch ( SQLException err ) {
			
			System.out.println( err.getMessage( ) );
			
		}
	
	}
	
	public void get_guild_nbgotkilled_from_player(){
		
		try{
			
			//con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",hr,hr);
			Statement stmt = con.createStatement();
			//ResultSet add = stmt.executeQuery("INSERT INTO PLAYER VALUES('Nidras', 'Alliance', 'Paladin', 'Retribution', 0, 0, 100, null, 10, 0, null, 'Le Petit Menhir')");
			ResultSet rs = stmt.executeQuery("SELECT UNIQUE GUILDS.NBGOTKILLED FROM GUILDS INNER JOIN PLAYERS ON PLAYERS.GUILD = GUILDS.NAME WHERE PLAYERS.GUILD = 'Le Petit Menhir'");
			
			int i = 0;
			while(rs.next()) {
				 
				  String lastName = rs.getString(1);
				  i ++;
				  //System.out.println(lastName + " " +  "\n");
				  
			}
			
		}
		
		catch(SQLException err){
		
			System.out.println(err.getMessage());
		
		}
		
	}

	public String requestString(String request){
		
		String result = "";
		
		try{
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(request);
			
			while(rs.next()){
				
				  result += rs.getString(1);
				  result += "\n";
				  
				  
			
			}
			
			//System.out.println(result + " OK " +  "\n");
			if(result != "") result = result.substring(0, result.length()-1);
		}
		
		catch(SQLException err) {
		
			System.out.println(err.getMessage());
		
		}
		
		return result;
		
	}
	
	public void checkAddGuild(String name, int nbgotkilled, int nbkilled){
		
		if(requestString("select name from guilds where name = '" + name + "'") == name){
			
			if(objectLists.findGuild(name) != null) return;
			else objectLists.addGuild(name);
			
		}
		
		else{
			
			String temp = requestString("INSERT INTO GUILDS VALUES ('" + name + "', " + nbgotkilled + ", " + nbkilled + ")");
			if(objectLists.findGuild(name) != null) return;
			else objectLists.addGuild(name);
			
		}
		
	}
	
	public void checkAddPlayer(String name, String guild, String faction, String classe, String specialization, int hpleft, String comment, int skill, boolean backped, int nbgotkilled, int nbkilled, String whisprage){
		
		if(requestString("select name from players where name = '" + name + "'") == name){
			
			if(objectLists.findPlayer(name) != null) return;
			else objectLists.addPlayer(name, guild, faction, classe, specialization, hpleft, comment, skill, backped);
			
		}
		
		else{
			
			String temp = requestString("INSERT INTO PLAYERS VALUES ('" + name + "','" + guild + "','" + faction + "','"  + classe + "','" + specialization + "'," + nbgotkilled + "," + nbkilled + "," + hpleft + ",'" + whisprage + "'," + skill + "," + backped + ",'" + comment + "'"  + ")");
			if(objectLists.findPlayer(name) != null) return;
			else objectLists.addPlayer(name, guild, faction, classe, specialization, hpleft, comment, skill, backped);
			
		}
		
	}
	
	public void checkAddPlace(String name, int x, int y){
		
		if(requestString("select name from places where name = '" + name + "' and x = " + x + " and y = " + y) == name){
			
			if(objectLists.findPlace(name, x, y) != null) return;
			else objectLists.addPlace(name, x, y);
			
		}
		
		else{
			
			String temp = requestString("INSERT INTO PLACES VALUES ('" + name + "', " + x + ", " + y + ")");
			if(objectLists.findPlace(name, x, y) != null) return;
			else objectLists.addPlace(name, x , y);
			
		}
		
	}
	
	public void checkAddTime(int year, int month, int day, int hour, int minute, int second){
		
		String timer = "" + year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
		
		String[] longtime = timer.split("-|\\:|\\ ");
		for(int i = 0 ; i < 6 ; i ++){
			
			if(longtime[i].length() == 1) longtime[i] = "0" + longtime[i];
			
		}
		timer = longtime[0] + "-" + longtime[1] + "-" + longtime[2] + " " + longtime[3] + ":" + longtime[4] + ":" + longtime[5];
		
		if(requestString("select TO_CHAR(time ,'YYYY-MM-DD HH24:MI:SS') from times where time = TO_DATE('" + timer + "', 'YYYY-MM-DD HH24:MI:SS')").matches(timer)){
			System.out.println("true");
			if(objectLists.findTime(timer) != null) return;
			else objectLists.addTime(year, month, day, hour, minute, second, timer);
			
		}
		
		else{
			
			String temp = requestString("INSERT INTO TIMES VALUES (TO_DATE('" + timer + "', 'YYYY-MM-DD HH24:MI:SS'))");
			if(objectLists.findTime(timer) != null) return;
			else objectLists.addTime(year, month, day, hour, minute, second, timer);
			
		}
		
	}
	
}