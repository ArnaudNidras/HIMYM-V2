package HIMYM;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Db {

	private Connection con;
	private String address;
	private String port;
	private String username;
	private String password;
	
	private GUI gui;
	
	public Db(String address, String port, String username, String password, GUI gui) throws ClassNotFoundException{
		
		this.gui = gui;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
			
			this.address = address;
			this.port = port;
			this.username = username;
			this.password = password;
			DriverManager.setLoginTimeout(2000);
			this.con = DriverManager.getConnection("jdbc:oracle:thin:@" + this.address + ":" + this.port + ":xe", this.username, this.password);
			
			this.gui.closeStartFrame();
			this.gui.start();	
			
		}
		
		catch ( SQLException err ) {
		
			System.out.println( err.getMessage( ) );
			this.gui.connectionError();
			
		}

	}
	
	public void connect(String address, String port, String username, String password) throws ClassNotFoundException{
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
			DriverManager.setLoginTimeout(2000);
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@" + address + ":" + port + ":xe", username, password);
			
			this.gui.closeStartFrame();
			this.gui.start();
			
		}
		
		catch ( SQLException err ) {
			
			System.out.println( err.getMessage( ) );
			this.gui.connectionError();
			
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
	
	public String getPlayerIDFromName(String name){
		
		return requestString("select player_id from players where name ='" + name + "'");
		
	}
	
	public String getPlayerNameFromID(String id){
		
		return requestString("select name from players where player_id =" + id);
		
	}
	
	public String getGuildIDFromName(String name){
		
		return requestString("select guild_id from guilds where name ='" + name + "'");
		
	}
	
	public String getGuildNameFromID(String id){
		
		return requestString("select name from guilds where guild_id =" + id);
		
	}
	
	public String getPlaceIDFromName(String name){
		
		return requestString("select place_id from places where name ='" + name + "'");
		
	}
	
	public String getPlaceNameFromID(String id){
		
		return requestString("select name from places where place_id =" + id);
		
	}
	
	public String getPlaceXFromID(String id){
		
		return requestString("select x from places where place_id =" + id);
		
	}
	
	public String getPlaceYFromID(String id){
		
		return requestString("select y from places where place_id =" + id);
		
	}
	
	public String getPlaceRegionFromID(String id){
		
		return requestString("select region from places where place_id =" + id);
		
	}
	
	public String getContinentFromPlaceID(String id){
		
		return requestString("select r.continent from places p, regions r where p.region = r.name and p.place_id =" + id);
		
	}
	
	public String getFightHpleftaFromID(String id){
		
		return requestString("select hplefta from fights where fight_id =" + id);
		
	}
	
	public String getFightHpleftbFromID(String id){
		
		return requestString("select hpleftb from fights where fight_id =" + id);
		
	}
	
	public String getNameFromPlayerA(String id){
		
		return requestString("select p.name from players p, fights f where p.player_id = f.playera and f.fight_id =" + id);
		
	}
	
	public String getNameFromPlayerB(String id){
		
		return requestString("select p.name from players p, fights f where p.player_id = f.playerb and f.fight_id =" + id);
		
	}
	
	public String getGuildNameFromPlayerA(String id){
		
		return getGuildNameFromID(requestString("select p.guild from players p, fights f where p.player_id = f.playera and f.fight_id =" + id));
		
	}
	
	public String getGuildNameFromPlayerB(String id){
		
		return getGuildNameFromID(requestString("select p.guild from players p, fights f where p.player_id = f.playerb and f.fight_id =" + id));
		
	}
	
	public String getServerFromPlayerA(String id){
		
		return requestString("select p.server from players p, fights f where p.player_id = f.playera and f.fight_id =" + id);
		
	}
	
	public String getServerFromPlayerB(String id){
		
		return requestString("select p.server from players p, fights f where p.player_id = f.playerb and f.fight_id =" + id);
		
	}
	
	public String getRaceFromPlayerA(String id){
		
		return requestString("select p.race from players p, fights f where p.player_id = f.playera and f.fight_id =" + id);
		
	}
	
	public String getRaceFromPlayerB(String id){
		
		return requestString("select p.race from players p, fights f where p.player_id = f.playerb and f.fight_id =" + id);
		
	}
	
	public String getFactionFromPlayerA(String id){
		
		return requestString("select p.faction from players p, fights f where p.player_id = f.playera and f.fight_id =" + id);
		
	}
	
	public String getFactionFromPlayerB(String id){
		
		return requestString("select p.faction from players p, fights f where p.player_id = f.playerb and f.fight_id =" + id);
		
	}
	
	public String getClassFromPlayerA(String id){
		
		return requestString("select p.classe from players p, fights f where p.player_id = f.playera and f.fight_id =" + id);
		
	}
	
	public String getClassFromPlayerB(String id){
		
		return requestString("select p.classe from players p, fights f where p.player_id = f.playerb and f.fight_id =" + id);
		
	}
	
	public String getSpecializationFromPlayerA(String id){
		
		return requestString("select p.specialization from players p, fights f where p.player_id = f.playera and f.fight_id =" + id);
		
	}
	
	public String getSpecializationFromPlayerB(String id){
		
		return requestString("select p.specialization from players p, fights f where p.player_id = f.playerb and f.fight_id =" + id);
		
	}
	
	public String getLevelFromPlayerA(String id){
		
		return requestString("select p.lvl from players p, fights f where p.player_id = f.playera and f.fight_id =" + id);
		
	}
	
	public String getLevelFromPlayerB(String id){
		
		return requestString("select p.lvl from players p, fights f where p.player_id = f.playerb and f.fight_id =" + id);
		
	}
	
	public String getTimeFromFight(String id){
		
		return requestString("select to_char(time, 'YYYY-MM-DD HH24:MI:SS') from fights where fight_id =" + id);
		
	}
	
	public String getLengthFromFight(String id){
		
		return requestString("select fightlength from fights where fight_id =" + id);
		
	}
	
	public String getCommentFromFight(String id){
		
		String temp = requestString("select fightcomment from fights where fight_id =" + id);
		
		if(temp.matches("null")) return "";
		
		else return temp;
		
	}
	
	//lvla, lvlb, servera, serverb, racea, raceb, classea, classeb, speca, specb
	
	public void addPlayer(String name, String level, String guild, String server, String faction, String race, String classe, String specialization, String nbkills, String nbdeaths, String whisprage, String skill, String backped, String skillcomment){
		
		if(requestString("select name from players where name ='" + name + "'") == ""){
			
			int id = Integer.valueOf(requestString("select MAX(player_id) from players")) + 1;
			
			requestString("insert into players (player_id, name, lvl, guild, server, faction, race, classe, specialization, nbkills, nbdeaths, skill, skillcomment, backped, whisprage) values (" + id + ",'" + name + "'," + level.toString() + "," + Integer.valueOf(getGuildIDFromName(guild)) + ",'" + server + "','" + faction + "','" + race + "','" + classe + "','" + specialization + "'," + Integer.valueOf(nbkills) + "," + Integer.valueOf(nbdeaths) + "," + skill + ",'" + skillcomment + "'," + backped + ",'" + whisprage + "')");
			
		}
		
	}
	
	public void addGuild(String name){
		
		if(requestString("select name from guilds where name ='" + name + "'") == ""){
			
			int id = Integer.valueOf(requestString("select MAX(guild_id) from guilds")) + 1;
			
			requestString("insert into guilds (guild_id, name) values (" + id + ",'" + name + "')");
			
		}
		
	}
	
	public void addRegion(String name, String continent){
		
		if(requestString("select name from regions where name ='" + name + "' and continent ='" + continent + "'") == ""){
			
			requestString("insert into regions (name, continent) values ('" + name + "','" + continent + "')");
			
		}
		
	}
	
	public void addPlace(int id, String name, String region, String continent, String x, String y){
		
			requestString("insert into places (place_id, name, region, x, y) values (" + id + ",'" + name + "','" + region + "'," + x + "," + y + ")");
		
	}
	
	public void addFight(String a, String b, String hplefta, String hpleftb, String winner, String loser, String time, String fightcomment, String fightlength, String continent, String region, String placename, String x, String y){
		
		if(requestString("select fight_id from fights where playera = " + getPlayerIDFromName(a) + " and playerb = " + getPlayerIDFromName(b) + " and to_char(time, 'YYYY-MM-DD HH24:MI:SS') = '" + time + "'").matches("")){
			
			int id = Integer.valueOf(requestString("select MAX(fight_id) from fights")) + 1;
			
			addRegion(region, continent);
			
			addPlace(id, placename, region, continent, x, y);
			
			requestString("insert into fights (fight_id, playera, playerb, hplefta, hpleftb, winner, loser, time, fightcomment, fightlength) values (" + id + "," + getPlayerIDFromName(a) + "," + getPlayerIDFromName(b) + "," + hplefta + "," + hpleftb + "," + getPlayerIDFromName(winner) + "," + getPlayerIDFromName(loser) + ", to_date('" + time + "', 'YYYY-MM-DD HH24:MI:SS'),'" + fightcomment + "','" + fightlength + "')");
			
			requestString("update players set nbkills = nbkills + 1 where player_id = " + winner);
			requestString("update players set nbdeaths = nbdeaths + 1 where player_id = " + loser);
			
		}
		
	}
	
	public void exportToCSV() throws IOException{
		
		int i = 0;
		
		FileWriter export = new FileWriter("Ressource/Export.csv");
		
		
		export.append(';');
		export.append("GUILDS\n");
		export.append("Guild_ID");
		export.append(';');
		export.append("Name");
		export.append("\n\n");
		
		int size = Integer.valueOf(requestString("select MAX(guild_id) from guilds"));
		
		for(i = 0 ; i <= size ; i ++ ){
			
			export.append(Integer.toString(i));
			export.append(';');
			export.append(requestString("select name from guilds where guild_id =" + i));
			
		}
		
		export.append("\n");
		
		export.append(';');
		export.append("PLAYERS\n");
		export.append("Player_ID");
		export.append(';');
		export.append("Name");
		export.append(';');
		export.append("Guild_ID");
		export.append(';');
		export.append("Server");
		export.append(';');
		export.append("Faction");
		export.append(';');
		export.append("Race");
		export.append(';');
		export.append("Classe");
		export.append(';');
		export.append("Specialization");
		export.append(';');
		export.append("Level");
		export.append(';');
		export.append("Skill x/10");
		export.append(';');
		export.append("Back Pedal");
		export.append(';');
		export.append("NB Kills");
		export.append(';');
		export.append("NB Deaths");
		export.append(';');
		export.append("Whisprage");
		export.append(';');
		export.append("Skill Comment");
		export.append("\n\n");
		
		size = Integer.valueOf(requestString("select MAX(player_id) from players"));
		
		for(i = 0 ; i <= size ; i ++ ){
			
			export.append(Integer.toString(i));
			export.append(';');
			export.append(requestString("select name from players where player_id =" + i));
			export.append(';');
			export.append(requestString("select guild from players where player_id =" + i));
			export.append(';');
			export.append(requestString("select server from players where player_id =" + i));
			export.append(';');
			export.append(requestString("select faction from players where player_id =" + i));
			export.append(';');
			export.append(requestString("select race from players where player_id =" + i));
			export.append(';');
			export.append(requestString("select classe from players where player_id =" + i));
			export.append(';');
			export.append(requestString("select specialization from players where player_id =" + i));
			export.append(';');
			export.append(requestString("select level from players where player_id =" + i));
			export.append(';');
			export.append(requestString("select skill from players where player_id =" + i));
			export.append(';');
			export.append(requestString("select backped from players where player_id =" + i));
			export.append(';');
			export.append(requestString("select nbkills from players where player_id =" + i));
			export.append(';');
			export.append(requestString("select nbdeaths from players where player_id =" + i));
			export.append(';');
			export.append(requestString("select whisprage from players where player_id =" + i));
			export.append(';');
			export.append(requestString("select skillcomment from players where player_id =" + i));
			
		}
		
		export.append("\n");
		
		export.append(';');
		export.append("PLACES\n");
		export.append("Place_ID");
		export.append(';');
		export.append("Name");
		export.append(';');
		export.append("X Coordinate");
		export.append(';');
		export.append("Y Coordinate");
		export.append(';');
		export.append("Region");
		export.append(';');
		export.append("Continent");
		export.append("\n\n");
		
		size = Integer.valueOf(requestString("select MAX(place_id) from places"));
		
		for(i = 0 ; i <= size ; i ++ ){
			
			export.append(Integer.toString(i));
			export.append(';');
			export.append(requestString("select name from places where place_id =" + i));
			export.append(';');
			export.append(requestString("select x from places where place_id =" + i));
			export.append(';');
			export.append(requestString("select y from places where place_id =" + i));
			export.append(';');
			export.append(requestString("select region from places where place_id =" + i));
			export.append(';');
			export.append(requestString("select r.continent from region r, places p where p.region = r.name and p.place_id =" + i));
			
		}
		
		export.append("\n");
		
		export.append(';');
		export.append("FIGHTS\n");
		export.append("Fight_ID");
		export.append(';');
		export.append("PlayerA_ID");
		export.append(';');
		export.append("PlayerB_ID");
		export.append(';');
		export.append("Winner_ID");
		export.append(';');
		export.append("Loser_ID");
		export.append(';');
		export.append("HP Left Player A");
		export.append(';');
		export.append("HP Left Player B");
		export.append(';');
		export.append("Time");
		export.append(';');
		export.append("Place_ID");
		export.append(';');
		export.append("Fight Comment");
		export.append(';');
		export.append("Fight Length");
		export.append("\n\n");
		
		size = Integer.valueOf(requestString("select MAX(fight_id) from fights"));
		
		for(i = 0 ; i <= size ; i ++ ){
			
			export.append(Integer.toString(i));
			export.append(';');
			export.append(requestString("select playera from fights where fight_id =" + i));
			export.append(';');
			export.append(requestString("select playerb from fights where fight_id =" + i));
			export.append(';');
			export.append(requestString("select winner from fights where fight_id =" + i));
			export.append(';');
			export.append(requestString("select loser from fights where fight_id =" + i));
			export.append(';');
			export.append(requestString("select hplefta from fights where fight_id =" + i));
			export.append(';');
			export.append(requestString("select hpleftb from fights where fight_id =" + i));
			export.append(';');
			export.append(requestString("select to_char(time, 'YYYY-MM-DD HH24:MI:SS') from fights where fight_id =" + i));
			export.append(';');
			export.append(Integer.toString(i));
			export.append(';');
			export.append(requestString("select fightcomment from fights where fight_id =" + i));
			export.append(';');
			export.append(requestString("select fightlength from fights where fight_id =" + i));
			
		}
		
		export.flush();
		export.close();
		
	}
	
}