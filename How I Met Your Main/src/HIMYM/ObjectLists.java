package HIMYM;

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
		
		initGuilds();
		initFactions();
		initPlayers();
		initPlaces();
		initTimes();
		initFights();
		
	}
	
	public void initGuilds(){
		
		String names = db.requestString("SELECT UNIQUE NAME FROM GUILDS");
		String[] name = names.split("\n");
		
		for(int i = 0 ; i < name.length ; i ++){
			
			guilds.add(new Guild(name[i]));
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
	
	public void addGuild(String name){
		
		guilds.add(new Guild(name));
		
	}
	
	public void initFactions(){
		
		String names = db.requestString("SELECT UNIQUE NAME FROM FACTIONS");
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
		
		String names = db.requestString("SELECT UNIQUE NAME FROM PLAYERS");
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
		
		for(int i = 0 ; i < name.length ; i ++){
			
			guildName = db.requestString("SELECT UNIQUE PLAYERS.GUILD FROM PLAYERS WHERE PLAYERS.NAME = '" + name[i] + "'");
			if(findGuild(guildName) == null) guilds.add(new Guild(guildName));
			guild = findGuild(guildName);
			
			factionName = db.requestString("SELECT UNIQUE PLAYERS.FACTION FROM PLAYERS WHERE PLAYERS.NAME = '" + name[i] + "'");
			faction = findFaction(factionName);
			
			classeName = db.requestString("SELECT UNIQUE PLAYERS.CLASSE FROM PLAYERS WHERE PLAYERS.NAME = '" + name[i] + "'").toUpperCase();
			classe = Classe.valueOf(classeName.toUpperCase());
			
			specializationName = db.requestString("SELECT UNIQUE PLAYERS.SPECIALIZATION FROM PLAYERS WHERE PLAYERS.NAME = '" + name[i] + "'").toUpperCase();
			specialization = Specialization.valueOf(specializationName);
			
			hpLeft = Integer.valueOf(db.requestString("SELECT UNIQUE PLAYERS.HPLEFT FROM PLAYERS WHERE PLAYERS.NAME = '" + name[i] + "'"));
			
			comment = db.requestString("SELECT UNIQUE PLAYERS.SKILLCOMMENT FROM PLAYERS WHERE PLAYERS.NAME = '" + name[i] + "'");
			
			skill = Integer.valueOf(db.requestString("SELECT UNIQUE PLAYERS.SKILL FROM PLAYERS WHERE PLAYERS.NAME = '" + name[i] + "'"));
			
			backped = Boolean.valueOf(db.requestString("SELECT UNIQUE PLAYERS.BACKPED FROM PLAYERS WHERE PLAYERS.NAME = '" + name[i] + "'"));
			
			players.add(new Player(name[i], guild, faction, classe, specialization, hpLeft, comment, skill, backped));
			System.out.println("New player Added : Name : " + name[i] + " Guilde : " + guildName + " Faction : " + factionName + " Class : " + classeName + " Specialization : " + specializationName + " hpLeft : " + hpLeft + " Comment : " + comment + " Skill : " + skill + "/10 Backpedal :  " + backped);
			
		}

	}
	
	public Player findPlayer(String name){
		
		if(players.isEmpty()) return null;
		
		for(int i = 0 ; i < players.size() ; i ++){
			
			if(players.get(i).getName() == name) return players.get(i);
			
		}
			
		return null;
		
	}

	public void addPlayer(String name, String guild, String faction, String classe, String specialization, int hpLeft,
			String comment, int skill, boolean backped){
		
		players.add(new Player(name, findGuild(guild), findFaction(faction), Classe.valueOf(classe.toUpperCase()), Specialization.valueOf(specialization.toUpperCase()), hpLeft, comment, skill, backped));
		
	}
	
	public void initPlaces(){
		
		String names = db.requestString("SELECT UNIQUE NAME FROM PLACES");
		String xs = db.requestString("SELECT UNIQUE Y FROM PLACES");
		String ys = db.requestString("SELECT UNIQUE Y FROM PLACES");
		
		String[] name = names.split("\n");

		String[] x = xs.split("\n");
		String[] y = ys.split("\n");
		
		for(int i = 0 ; i < name.length ; i ++){
			
			places.add(new Place(name[i], Integer.valueOf(x[i]), Integer.valueOf(y[i])));
			System.out.println("New place added : " + name[i] + " x : " + Integer.valueOf(x[i]) + " y : " + Integer.valueOf(y[i]) + " !");
			
		}

		
	}
	
	public Place findPlace(String name, int x, int y){
		
		if(places.isEmpty()) return null;
		
		for(int i = 0 ; i < places.size() ; i ++){
			
			if(places.get(i).getName() == name && places.get(i).getX() == x && places.get(i).getY() == y) return places.get(i);
			
		}
			
		return null;
		
	}

	public void addPlace(String name, int x, int y){
		
		places.add(new Place(name, x, y));
		
	}
	
	public void initTimes(){
		
		String time = db.requestString("SELECT UNIQUE TO_CHAR(TIME ,'YYYY-MM-DD HH24:MI:SS') FROM TIMES");
		
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
			
			if(times.get(i).getTiming() == timing) return times.get(i);
			
		}
		
		return null;
		
	}
	
	public void addTime(int year, int month, int day, int hour, int minute, int second, String timing){
		
		times.add(new Time(year, month, day, hour, minute, second, timing));
		
	}
	
	public void initFights(){
		
		//Player a, Player b, Player winner, Player loser, Time time, Place place, String fight_comment, int fight_length
		
		String names = db.requestString("SELECT UNIQUE TO_CHAR(TIME ,'YYYY-MM-DD HH24:MI:SS') FROM FIGHTS");
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
		
		for(int i = 0 ; i < name.length ; i ++){

			aName = db.requestString("SELECT UNIQUE FIGHTS.PLAYERA FROM FIGHTS WHERE FIGHTS.TIME = TO_DATE('" + name[i] + "', 'YYYY-MM-DD HH24:MI:SS')");
			a = findPlayer(aName);
			
			bName = db.requestString("SELECT UNIQUE FIGHTS.PLAYERB FROM FIGHTS WHERE FIGHTS.TIME = TO_DATE('" + name[i] + "', 'YYYY-MM-DD HH24:MI:SS')");
			b = findPlayer(bName);
			
			wName = db.requestString("SELECT UNIQUE FIGHTS.WINNER FROM FIGHTS WHERE FIGHTS.TIME = TO_DATE('" + name[i] + "', 'YYYY-MM-DD HH24:MI:SS')");
			w = findPlayer(wName);
			
			lName = db.requestString("SELECT UNIQUE FIGHTS.LOSER FROM FIGHTS WHERE FIGHTS.TIME = TO_DATE('" + name[i] + "', 'YYYY-MM-DD HH24:MI:SS')");
			l = findPlayer(lName);
			
			timing = db.requestString("SELECT UNIQUE TO_CHAR(FIGHTS.TIME ,'YYYY-MM-DD HH24:MI:SS') FROM FIGHTS WHERE FIGHTS.TIME = TO_DATE('" + name[i] + "', 'YYYY-MM-DD HH24:MI:SS')");
			time = findTime(timing);

			placeName = db.requestString("SELECT UNIQUE FIGHTS.PLACE FROM FIGHTS WHERE FIGHTS.TIME = TO_DATE('" + name[i] + "', 'YYYY-MM-DD HH24:MI:SS')");
			x = Integer.valueOf(db.requestString("SELECT UNIQUE FIGHTS.X FROM FIGHTS WHERE FIGHTS.TIME = TO_DATE('" + name[i] + "', 'YYYY-MM-DD HH24:MI:SS')"));
			y = Integer.valueOf(db.requestString("SELECT UNIQUE FIGHTS.Y FROM FIGHTS WHERE FIGHTS.TIME = TO_DATE('" + name[i] + "', 'YYYY-MM-DD HH24:MI:SS')"));
			place = findPlace(placeName, x, y);
			
			fight_comment = db.requestString("SELECT UNIQUE FIGHTS.FIGHTCOMMENT FROM FIGHTS WHERE FIGHTS.TIME = TO_DATE('" + name[i] + "', 'YYYY-MM-DD HH24:MI:SS')");
			
			fight_length = Integer.valueOf(db.requestString("SELECT UNIQUE FIGHTS.FIGHTLENGTH FROM FIGHTS WHERE FIGHTS.TIME = TO_DATE('" + name[i] + "', 'YYYY-MM-DD HH24:MI:SS')"));
			
			fights.add(new Fight(a, b, w, l, time, place, fight_comment, fight_length));
			System.out.println("New fight Added : Player a : " + aName + " Player b :  " + bName + " Winner : " + wName + " Loser : " + lName + " Time : " + timing + " Place : " + placeName + " x : " + x + " y : " + y + " Fight Comment : " + fight_comment + " Fight Length : " + fight_length);
			
		}
		
	}
	
}
