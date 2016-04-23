package HIMYM;

import java.sql.SQLException;
import java.util.LinkedList;


public class ObjectLists {

	private Db db;
	
	private LinkedList<Guild> guilds;
	private LinkedList<Player> players;
	private LinkedList<Place> places;
	private LinkedList<Time> times;
	private LinkedList<Fight> fights;
	private LinkedList<Faction> factions;
	
	public ObjectLists(Db db) throws ClassNotFoundException{
		
		this.db = db;
		
		this.guilds = new LinkedList<Guild>();
		this.factions = new LinkedList<Faction>();
		this.players = new LinkedList<Player>();
		this.places = new LinkedList<Place>();
		this.times = new LinkedList<Time>();
		this.fights = new LinkedList<Fight>();
		
	}
	
	public void loadEverything(){
		try {
			db.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			db.connect();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cleanEverything();
		initGuilds();
		initFactions();
		initPlayers();
		initPlaces();
		initTimes();
		initFights();
		
		
	}
	
	public void cleanEverything(){
		
		guilds.clear();
		players.clear();
		places.clear();
		times.clear();
		fights.clear();
		
	}
	
	public void initGuilds(){
		
		String names = db.requestString("SELECT NAME FROM GUILDS");
		String[] name = names.split("\n");
		String nbgotkilled = db.requestString("SELECT NBGOTKILLED FROM GUILDS");
		String nbkilled = db.requestString("SELECT NBKILLED FROM GUILDS");
		String[] nbgk = nbgotkilled.split("\n");
		String[] nbk = nbkilled.split("\n");
		
		for(int i = 0 ; i < name.length ; i ++){
			
			guilds.add(new Guild(name[i], Integer.valueOf(nbgk[i]), Integer.valueOf(nbk[i])));
			System.out.println("New guild added : " + name[i] + " !");
			
		}
		
	}
	
	public Guild findGuild(String name){
		
		if(guilds.isEmpty()) return null;
		
		for(int i = 0 ; i < guilds.size() ; i ++){
			
			if(guilds.get(i).getName() == name) return guilds.get(i);
			
		}
			
		return null;
		
	}
	
	public void addGuild(String name, int nbgotkilled, int nbkilled){
		
		guilds.add(new Guild(name, nbgotkilled, nbkilled));
		
	}
	
	public void initFactions(){
		
		String names = db.requestString("SELECT NAME FROM FACTIONS");
		String[] name = names.split("\n");
		
		for(int i = 0 ; i < name.length ; i ++){
			
			factions.add(new Faction(name[i]));
			System.out.println("New faction added : " + name[i] + " !");
			
		}

		
	}

	public Faction findFaction(String name){
		
		if(factions.isEmpty()) return null;
		
		for(int i = 0 ; i < factions.size() ; i ++){
			
			if(factions.get(i).getName() == name) return factions.get(i);
			
		}
			
		return null;
		
	}

	public void initPlayers(){
		
		String names = db.requestString("SELECT NAME FROM PLAYERS");
		String[] name = names.split("\n");
		
		String guildName;
		Guild guild;
		String factionName;
		Faction faction;
		String classeName;
		Classe classe;
		String specializationName;
		Specialization specialization;
		int hpLeft;
		String comment;
		int skill;
		boolean backped;
		
		for(int i = 1 ; i < name.length + 1 ; i ++){
			
			
			names = db.requestString("SELECT NAME from (select p.NAME, rownum r from PLAYERS p) where r = " + i);
			
			guildName = db.requestString("SELECT PLAYERS.GUILD FROM PLAYERS WHERE PLAYERS.NAME = '" + name[i-1] + "'");
			int nbgk = Integer.valueOf(db.requestString("SELECT NBGOTKILLED FROM GUILDS WHERE NAME = '" + guildName + "'"));
			int nbk = Integer.valueOf(db.requestString("SELECT  NBKILLED FROM GUILDS WHERE NAME = '" + guildName + "'"));
			if(findGuild(guildName) == null) guilds.add(new Guild(guildName, nbgk, nbk));
			guild = findGuild(guildName);
			
			factionName = db.requestString("SELECT PLAYERS.FACTION FROM PLAYERS WHERE PLAYERS.NAME = '" + name[i-1] + "'");
			faction = findFaction(factionName);
			
			classeName = db.requestString("SELECT PLAYERS.CLASSE FROM PLAYERS WHERE PLAYERS.NAME = '" + name[i-1] + "'").toUpperCase();
			classe = Classe.valueOf(classeName.toUpperCase());
			
			specializationName = db.requestString("SELECT PLAYERS.SPECIALIZATION FROM PLAYERS WHERE PLAYERS.NAME = '" + name[i-1] + "'").toUpperCase();
			specialization = Specialization.valueOf(specializationName);
			
			hpLeft = Integer.valueOf(db.requestString("SELECT PLAYERS.HPLEFT FROM PLAYERS WHERE PLAYERS.NAME = '" + name[i-1] + "'"));
			
			comment = db.requestString("SELECT PLAYERS.SKILLCOMMENT FROM PLAYERS WHERE PLAYERS.NAME = '" + name[i-1] + "'");
			
			skill = Integer.valueOf(db.requestString("SELECT PLAYERS.SKILL FROM PLAYERS WHERE PLAYERS.NAME = '" + name[i-1] + "'"));
			
			backped = Boolean.valueOf(db.requestString("SELECT PLAYERS.BACKPED FROM PLAYERS WHERE PLAYERS.NAME = '" + name[i-1] + "'"));
			
			players.add(new Player(names, guild, faction, classe, specialization, hpLeft, comment, skill, backped));
			System.out.println("New player Added : Name : " + names + " Guilde : " + guildName + " Faction : " + factionName + " Class : " + classeName + " Specialization : " + specializationName + " hpLeft : " + hpLeft + " Comment : " + comment + " Skill : " + skill + "/10 Backpedal :  " + backped);
			
		}

	}
	
	public Player findPlayer(String name){
		
		if(players.isEmpty()) return null;
		
		for(int i = 0 ; i < players.size() ; i ++){
			
			if(players.get(i).getName().matches(name)) return players.get(i);
			
		}
		
		return null;
		
	}

	public void addPlayer(String name, String guild, String faction, String classe, String specialization, int hpLeft,
			String comment, int skill, boolean backped){
		
		players.add(new Player(name, findGuild(guild), findFaction(faction), Classe.valueOf(classe.toUpperCase()), Specialization.valueOf(specialization.toUpperCase()), hpLeft, comment, skill, backped));
				
	}
	
	public void initPlaces(){
		
		String names = db.requestString("SELECT NAME FROM PLACES");
		String xs;
		String ys;
		
		String[] name = names.split("\n");

		for(int i = 1 ; i < name.length + 1 ; i ++){
			
			xs = db.requestString("SELECT X from (select p.X, rownum r from PLACES p) where r = " + i);
			ys = db.requestString("SELECT Y from (select p.Y, rownum r from PLACES p) where r = " + i);
			names = db.requestString("SELECT NAME from (select p.NAME, rownum r from PLACES p) where r = " + i);
			
			places.add(new Place(names, Integer.valueOf(xs), Integer.valueOf(ys)));
			System.out.println("New place added : " + names + " x : " + Integer.valueOf(xs) + " y : " + Integer.valueOf(ys) + " !");
			
		}
		
	}
	
	public Place findPlace(String name, int x, int y){
		
		if(places.isEmpty()) return null;
		
		for(int i = 0 ; i < places.size() ; i ++){
			
			if(places.get(i).getName().matches(name) && places.get(i).getX() == x && places.get(i).getY() == y) return places.get(i);
			
		}
			
		return null;
		
	}

	public void addPlace(String name, int x, int y){
		
		places.add(new Place(name, x, y));
		
	}
	
	public void initTimes(){
		
		String time = db.requestString("SELECT TO_CHAR(TIME ,'YYYY-MM-DD HH24:MI:SS') FROM TIMES");
		
		String[] all = time.split("\n");
		
		for(int i = 0 ; i < all.length ; i ++){
			
			String[] longtime = all[i].split("-|\\:|\\ ");
			int year = Integer.valueOf(longtime[0]);
			int month = Integer.valueOf(longtime[1]);
			int day = Integer.valueOf(longtime[2]);
			int hour = Integer.valueOf(longtime[3]);;
			int minute = Integer.valueOf(longtime[4]);
			int second = Integer.valueOf(longtime[5]);

			times.add(new Time(year, month, day, hour, minute, second, all[i]));
			System.out.println("New time added : " + " year : " + year + " month : " + month + " day : " + day + " hour : " + hour + " minute : " + minute + " second : " + second + " time : " + all[i] + " !");
			
		}
		
	}

	public Time findTime(String timing){
		
		if(times.isEmpty()) return null;
		
		for(int i = 0 ; i < times.size() ; i ++){
			
			if(times.get(i).getTiming().matches(timing)) return times.get(i);
			
		}
		
		return null;
		
	}
	
	public void addTime(int year, int month, int day, int hour, int minute, int second, String timing){
		
		times.add(new Time(year, month, day, hour, minute, second, timing));
		
	}
	
	public void initFights(){
				
		String names = db.requestString("SELECT TO_CHAR(TIME ,'YYYY-MM-DD HH24:MI:SS') FROM FIGHTS");
		String[] name = names.split("\n");
		
		String aName;
		Player a;
		String bName;
		Player b;
		String wName;
		Player w;
		String lName;
		Player l;
		String timing;
		Time time;
		String placeName;
		int x;
		int y;
		Place place;
		String fight_comment;
		int fight_length;
		
		for(int i = 1 ; i < name.length + 1 ; i ++){

			aName = db.requestString("SELECT PLAYERA from (select f.PLAYERA, rownum r from FIGHTS f) where r = " + i);
			a = findPlayer(aName);
			
			bName = db.requestString("SELECT PLAYERB from (select f.PLAYERB, rownum r from FIGHTS f) where r = " + i);
			b = findPlayer(bName);
			
			wName = db.requestString("SELECT WINNER from (select f.WINNER, rownum r from FIGHTS f) where r = " + i);
			w = findPlayer(wName);
			
			lName = db.requestString("SELECT LOSER from (select f.LOSER, rownum r from FIGHTS f) where r = " + i);
			l = findPlayer(lName);
			
			timing = db.requestString("SELECT TO_CHAR(TIME ,'YYYY-MM-DD HH24:MI:SS') from (select f.TIME, rownum r from FIGHTS f) where r = " + i);
			time = findTime(timing);

			placeName = db.requestString("SELECT PLACE from (select f.PLACE, rownum r from FIGHTS f) where r = " + i);
			x = Integer.valueOf(db.requestString("SELECT X from (select f.X, rownum r from FIGHTS f) where r = " + i));
			y = Integer.valueOf(db.requestString("SELECT Y from (select f.Y, rownum r from FIGHTS f) where r = " + i));
			place = findPlace(placeName, x, y);
			
			fight_comment = db.requestString("SELECT FIGHTCOMMENT from (select f.FIGHTCOMMENT, rownum r from FIGHTS f) where r = " + i);
			
			fight_length = Integer.valueOf(db.requestString("SELECT FIGHTLENGTH from (select f.FIGHTLENGTH, rownum r from FIGHTS f) where r = " + i));
			
			fights.add(new Fight(a, b, w, l, time, place, fight_comment, fight_length));
			System.out.println("New fight Added : Player a : " + a.getName() + " Player b :  " + b.getName() + " Winner : " + w.getName() + " Loser : " + l.getName() + " Time : " + timing + " Place : " + place.getName() + " x : " + x + " y : " + y + " Fight Comment : " + fight_comment + " Fight Length : " + fight_length);

		}
		
	}
	
	public Fight findFight(String a, String b, String winner, String loser, String place, int x, int y, String time){
		
		if(fights.isEmpty()) return null;
		
		for(int i = 0 ; i < fights.size() ; i ++){

			if(fights.get(i).getPlayerA().getName() == a && fights.get(i).getPlayerB().getName() == b && fights.get(i).getWinner().getName() == winner && fights.get(i).getLoser().getName() == loser && fights.get(i).getPlace().getName() == place && fights.get(i).getPlace().getX() == x && fights.get(i).getPlace().getY() == y && fights.get(i).getTime().getTiming() == time) return fights.get(i);
						
		}
			
		return null;
		
	}
	
	public void addFight(String a, String b, String winner, String loser, String place, int x, int y, String time, String fight_comment, int fight_length){
		
		fights.add(new Fight(findPlayer(a), findPlayer(b), findPlayer(winner), findPlayer(loser), findTime(time), findPlace(place, x, y), fight_comment, fight_length));
		
	}
	
}
