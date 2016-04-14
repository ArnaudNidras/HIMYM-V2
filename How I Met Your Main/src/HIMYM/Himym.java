package HIMYM;

public class Himym {

	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		// faire classe fonction statistiques et bdd (init dans main)
		// faire nombre de rencontre - nombre de mort pour combat sans issue
		
		Db db = new Db();
		GUI gui = new GUI(db);
		
		Faction alliance = new Faction("Alliance");
		Faction horde = new Faction("Horde");
		
		Player irinawen = new Player("Irinawen", new Guild("Le Petit Mehnir"), alliance, Classe.PALADIN, Specialization.RETRIBUTION, 152000, "Moi", 9, false);
		//Player uxellodunon = new Player("Uxellodunon", new Guild("Le Petit Mehnir"), alliance, Class.DRUID, Specialization.FERAL, 0, "HANS ZELOT", 2, true);
		
		//Fight test = new Fight(irinawen, uxellodunon, irinawen, uxellodunon, new Time(2016, 03, 15, 13, 38, 24), new Place("I14", 5, 5), 4);
		
		
		
		db.get_guild_nbgotkilled_from_player();
		
		

	}

}
