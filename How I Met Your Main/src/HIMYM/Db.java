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
			
			if(result != "") result = result.substring(0, result.length()-1);
		}
		
		catch(SQLException err) {
		
			System.out.println(err.getMessage());
		
		}
		
		return result;
		
	}
	
	public void checkAddGuild(String name, int nbgotkilled, int nbkilled){
		
		if(requestString("select name from guilds where name = '" + name + "'") != ""){
			
			if(objectLists.findGuild(name) != null) return;
			else objectLists.addGuild(name, nbgotkilled, nbkilled);
			
		}
		
		else{
			
			String temp = requestString("INSERT INTO GUILDS VALUES ('" + name + "', " + nbgotkilled + ", " + nbkilled + ")");
			if(objectLists.findGuild(name) != null) return;
			else objectLists.addGuild(name, nbgotkilled, nbkilled);
			
		}
		
	}
	
	public void checkAddPlayer(String name, String guild, String faction, String classe, String specialization, String comment, int skill, int backped, int nbgotkilled, int nbkilled, String whisprage, int level, String race){
		
		boolean bp;
    	if(backped == 0) bp = false;
    	else bp = true;
		
		if(requestString("select name from players where name = '" + name + "'") != ""){
			
			if(objectLists.findPlayer(name) != null) return;
			else objectLists.addPlayer(name, guild, faction, classe, specialization, comment, skill, bp, level, race);
			
		}
		
		else{
			//System.out.println("INSERT INTO PLAYERS VALUES ('" + name + "','" + guild + "','" + faction + "','"  + classe + "','" + specialization + "'," + nbgotkilled + "," + nbkilled + ",'" + whisprage + "'," + skill + "," + backped + ",'" + comment + "')");
			String temp = requestString("INSERT INTO PLAYERS VALUES ('" + name + "','" + guild + "','" + faction + "','"  + classe + "','" + specialization + "'," + nbgotkilled + "," + nbkilled + ",'" + whisprage + "'," + skill + "," + backped + ",'" + comment + "','" + race + "'," + level + ")");
			//System.out.println("After");
			if(objectLists.findPlayer(name) != null) return;
			else objectLists.addPlayer(name, guild, faction, classe, specialization, comment, skill, bp, level, race);
			
		}
		
	}
	
	public void checkPlayerDB(String name){
		
		//System.out.println("test1");
		
		if(requestString("select name from players where name = '" + name + "'") != ""){
			
			//System.out.println("test2");
			
			if(objectLists.findPlayer(name) != null) return;
			else{
			
				String guild = requestString("select guild from players where name = '" + name + "'");
				String faction = requestString("select faction from players where name = '" + name + "'");
				String classe = requestString("select classe from players where name = '" + name + "'");
				String specialization = requestString("select specialization from players where name = '" + name + "'");
				//int hpleft = Integer.valueOf(requestString("select hpleft from players where name = '" + name + "'"));
				String comment = requestString("select skillcomment from players where name = '" + name + "'");
				int skill = Integer.valueOf(requestString("select skill from players where name = '" + name + "'"));
				int level = Integer.valueOf(requestString("select lvl from players where name = '" + name + "'"));
				String race = requestString("select race from players where name = '" + name + "'");
				int backped1 = Integer.valueOf(requestString("select backped from players where name = '" + name + "'"));
				boolean backped;
				if(backped1 == 1) backped = true;
				else backped = false;
				
				
				int nbgk = Integer.valueOf(requestString("select nbgotkilled from guilds where name = '" + guild + "'"));
				int nbk = Integer.valueOf(requestString("select nbkilled from guilds where name = '" + guild + "'"));
				checkAddGuild(guild, nbgk, nbk);
				
				//System.out.println("AddPlayerDB : " + name + " " + guild + " " + faction + " " + classe + " " + specialization/* + " " + hpleft*/ + " " + comment + " " + skill + " " + backped);
				objectLists.addPlayer(name, guild, faction, classe, specialization/*, hpleft*/, comment, skill, backped, level, race);
				
			}
			
		}
		
	}
	
	public void checkAddPlace(String name, int x, int y){
		
		if(requestString("select name from places where name = '" + name + "' and x = " + x + " and y = " + y) != ""){
			
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
	
	public void checkAddFight(String a, String b, String winner, String loser, String time, String place, int x, int y, String fight_comment, int fight_length, int hplefta, int hpleftb){
		
		if(requestString("select winner from fights where playera = '" + a + "' and playerb = '" + b + "' and x = " + x + " and y = " + y + " and place = '" + place + "' and time = '" + time + "' and loser = '" + loser + "' and hplefta = '" + hplefta + "' and hpleftb = '" + hpleftb + "'") == winner){
			
			if(objectLists.findFight(a, b, winner, loser, place, x, y, time, hplefta, hpleftb) != null) return;
			else objectLists.addFight(a, b, winner, loser, place, x, y, time, fight_comment, fight_length, hplefta, hpleftb);
			
		}
		
		else{
			
			//System.out.println("INSERT INTO FIGHTS VALUES ('" + a + "', '" + b + "', '" + winner + "', '" + loser + "', TO_DATE('" + time + "', 'YYYY-MM-DD HH24:MI:SS'), '" + place + "', '" + fight_comment + "', " + fight_length + ", " + x + ", " + y);
			String temp = requestString("INSERT INTO FIGHTS VALUES ('" + a + "', '" + b + "', '" + winner + "', '" + loser + "', TO_DATE('" + time + "', 'YYYY-MM-DD HH24:MI:SS'), '" + place + "', '" + fight_comment + "', " + fight_length + ", " + x + ", " + y + ", " + hplefta + ", " + hpleftb + ")");
			if(objectLists.findFight(a, b, winner, loser, place, x, y, time, hplefta, hpleftb) != null) return;
			else objectLists.addFight(a, b, winner, loser, place, x, y, time, fight_comment, fight_length, hplefta, hpleftb);
			
		}
		
	}
	
}